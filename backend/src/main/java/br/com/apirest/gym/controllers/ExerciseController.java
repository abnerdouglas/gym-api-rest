package br.com.apirest.gym.controllers;

import br.com.apirest.gym.models.Exercise;
import br.com.apirest.gym.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ExerciseController {
    @Autowired
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getExercises(
            @RequestParam(required = false) String muscle,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String difficulty) {

        List<Exercise> exercises = exerciseService.getExercises(muscle, type, difficulty);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }
}
