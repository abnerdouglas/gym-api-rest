package br.com.apirest.gym.services;

import br.com.apirest.gym.infra.api.WgerApiClient;
import br.com.apirest.gym.models.Exercise;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExerciseService {

    @Autowired
    private final WgerApiClient wgerApiClient;

    @Autowired
    public ExerciseService(WgerApiClient wgerApiClient) {
        this.wgerApiClient = wgerApiClient;
    }

    public List<Exercise> getWorkoutPlan(String muscleGroupName) {
        List<Exercise> workoutPlan = new ArrayList<>();
        try {
            Map<String, Integer> muscleGroups = wgerApiClient.getMuscleGroups();
            if (muscleGroups.containsKey(muscleGroupName)) {
                int muscleGroupId = muscleGroups.get(muscleGroupName);
                workoutPlan.addAll(wgerApiClient.getExercisesForMuscleGroup(muscleGroupId, muscleGroupName));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return workoutPlan;
    }
}