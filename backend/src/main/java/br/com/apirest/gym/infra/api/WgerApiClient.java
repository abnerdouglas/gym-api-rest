package br.com.apirest.gym.infra.api;

import br.com.apirest.gym.models.Exercise;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WgerApiClient {
    @Value("${wger.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://wger.de/api/v2/";

    public Map<String, Integer> getMuscleGroups() throws IOException, ParseException {
        Map<String, Integer> muscleGroups = new HashMap<>();
        String muscleGroupsUrl = BASE_URL + "muscle/";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(muscleGroupsUrl);
            request.setHeader("Authorization", "Token " + apiKey);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode muscles = objectMapper.readTree(jsonResponse).get("results");

                for (JsonNode muscle : muscles) {
                    String name = muscle.get("name").asText();
                    int id = muscle.get("id").asInt();
                    muscleGroups.put(name, id);
                }
            }
        }
        return muscleGroups;
    }

    public List<Exercise> getExercisesForMuscleGroup(int muscleGroupId, String muscleGroupName) throws IOException, ParseException {
        List<Exercise> exercises = new ArrayList<>();
        String exercisesUrl = BASE_URL + "exercise/?muscles=" + muscleGroupId;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(exercisesUrl);
            request.setHeader("Authorization", "Token " + apiKey);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode results = objectMapper.readTree(jsonResponse).get("results");

                for (JsonNode result : results) {
                    String name = result.get("name").asText();
                    String specificPart = "Medial"; // exemplo fixo
                    int sets = 3; // exemplo fixo
                    int reps = 12; // exemplo fixo
                    int estimatedTime = 10; // exemplo fixo
                    exercises.add(new Exercise(name, muscleGroupName, specificPart, sets, reps, estimatedTime));
                }
            }
        }
        return exercises;
    }
}
