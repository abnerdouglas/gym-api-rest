package br.com.apirest.gym.services;

import br.com.apirest.gym.models.Exercise;
import br.com.apirest.gym.validations.ValidationDifficulty;
import br.com.apirest.gym.validations.ValidationExerciseType;
import br.com.apirest.gym.validations.ValidationMuscleGroup;
import br.com.apirest.gym.validations.ValidationParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ExerciseService {
    @Value("${API_URL}")
    private String API_URL;
    @Value("${API_KEY}")
    private String API_KEY;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ValidationMuscleGroup validationMuscleGroup;
    @Autowired
    private ValidationExerciseType validationExerciseType;
    @Autowired
    private ValidationDifficulty validationDifficulty;
    @Autowired
    private ValidationParameter validationParameter;

    public List<Exercise> getExercises(String muscle, String type, String difficulty) {
        validateQueryParams(muscle, type, difficulty);
        String queryString = buildQueryString(muscle, type, difficulty);
        String url = API_URL + "?" + queryString;

        ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                new ParameterizedTypeReference<List<Exercise>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error: " + response.getStatusCode() + " " + response.getBody());
        }
    }

    private void validateQueryParams(String muscle, String type, String difficulty) {
        List<String> params = Arrays.asList(muscle, type, difficulty);
        long nonNullCount = params.stream().filter(Objects::nonNull).count();

        if (nonNullCount != 1) {
            throw new IllegalArgumentException("Você deve usar apenas um dos parâmetros: 'muscle', 'type' ou 'difficulty'.");
        }

        if (muscle != null) {
            validationParameter.validate("muscle");
            validationMuscleGroup.validate(muscle);
        } else if (type != null) {
            validationParameter.validate("type");
            validationExerciseType.validate(type);
        } else {
            validationParameter.validate("difficulty");
            validationDifficulty.validate(difficulty);
        }
    }

    private void validateParam(String param) {
        if (param == null) {
            return;
        }

        if (param.equals("muscle")) {
            validationMuscleGroup.validate(param);
        } else if (param.equals("type")) {
            validationExerciseType.validate(param);
        } else if (param.equals("difficulty")) {
            validationDifficulty.validate(param);
        } else {
            throw new IllegalArgumentException("Parametro inválido: " + param);
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);
        headers.set("Accept", "application/json");
        return headers;
    }

    private String buildQueryString(String muscle, String type, String difficulty) {
        if (muscle != null) {
            return "muscle=" + muscle;
        } else if (type != null) {
            return "type=" + type;
        } else if (difficulty != null) {
            return "difficulty=" + difficulty;
        }
        return "";
    }
}