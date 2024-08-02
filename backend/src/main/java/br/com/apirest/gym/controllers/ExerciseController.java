package br.com.apirest.gym.controllers;

import br.com.apirest.gym.models.Exercise;
import br.com.apirest.gym.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExerciseController {
    @Autowired
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/workout-plan")
    public List<Exercise> getWorkoutPlan(@RequestParam String muscleGroup) {
        return exerciseService.getWorkoutPlan(muscleGroup);
    }
}
