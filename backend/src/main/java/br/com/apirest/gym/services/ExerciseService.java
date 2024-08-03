package br.com.apirest.gym.services;

import br.com.apirest.gym.models.Exercise;
import br.com.apirest.gym.validations.ValidationDifficulty;
import br.com.apirest.gym.validations.ValidationExerciseType;
import br.com.apirest.gym.validations.ValidationMuscleGroup;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExerciseService {

    private static final String API_URL_TEMPLATE = "https://api.api-ninjas.com/v1/exercises";
    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("API_KEY"); // Substitua pela sua chave da API
    private final RestTemplate restTemplate;
    public ExerciseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Exercise> getExercises(Map<String, String> queryParams) {
        // Configurar os cabeçalhos da solicitação
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY); // Adiciona a chave da API ao cabeçalho
        headers.set("Accept", "application/json"); // Solicita resposta no formato JSON

        // Configura a entidade com os cabeçalhos
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Validação dos parâmetros
        ValidationMuscleGroup validationMuscleGroup = new ValidationMuscleGroup();
        ValidationExerciseType validationExerciseType = new ValidationExerciseType();
        ValidationDifficulty validationDifficulty = new ValidationDifficulty();

        String muscle = queryParams.get("muscle");
        String type = queryParams.get("type");
        String difficulty = queryParams.get("difficulty");

        // Verifica se apenas um dos parâmetros está presente
        if (queryParams.size() != 1) {
            throw new IllegalArgumentException("Você deve usar apenas um dos parâmetros: 'muscle', 'type' ou 'difficulty'.");
        }

        if (muscle != null) {
            validationMuscleGroup.validate(muscle);
        }

        if (type != null) {
            validationExerciseType.validate(type);
        }

        if (difficulty != null) {
            validationDifficulty.validate(difficulty);
        }

        // Construir a string de consulta a partir dos parâmetros
        String queryString = queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        // Adiciona a string de consulta à URL
        String url = API_URL_TEMPLATE + "?" + queryString;

        // Faz a solicitação GET para a API
        ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Exercise>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error: " + response.getStatusCode() + " " + response.getBody());
        }
    }
}