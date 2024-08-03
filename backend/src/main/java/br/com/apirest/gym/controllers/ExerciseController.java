package br.com.apirest.gym.controllers;

import br.com.apirest.gym.models.Exercise;
import br.com.apirest.gym.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ExerciseController {
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

        // Cria um mapa com os parâmetros fornecidos
        Map<String, String> queryParams = new HashMap<>();

        if (muscle != null) {
            queryParams.put("muscle", muscle);
        }

        if (type != null) {
            queryParams.put("type", type);
        }

        if (difficulty != null) {
            queryParams.put("difficulty", difficulty);
        }

        // Valida e faz a solicitação ao serviço
        if (queryParams.size() != 1) {
            throw new IllegalArgumentException("Você deve usar apenas um dos parâmetros: 'muscle', 'type' ou 'difficulty'");
        }

        List<Exercise> exercises = exerciseService.getExercises(queryParams);
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }
}
