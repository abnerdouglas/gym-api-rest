package br.com.apirest.gym.validations;

import br.com.apirest.gym.exceptions.MuscleGroupException;

import java.util.Arrays;
import java.util.List;

public class ValidationMuscleGroup implements Validation {

    private static final List<String> MUSCLE_GROUPS = Arrays.asList(
            "abdominals",
            "abductors",
            "adductors",
            "biceps",
            "calves",
            "chest",
            "forearms",
            "glutes",
            "hamstrings",
            "lats",
            "lower_back",
            "middle_back",
            "neck",
            "quadriceps",
            "traps",
            "triceps"
    );

    public void validate(String muscle) {
        if (!MUSCLE_GROUPS.contains(muscle)) {
            throw new MuscleGroupException("Grupo muscular inválido: " + muscle);
        }
    }

}
