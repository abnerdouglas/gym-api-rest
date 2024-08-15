package br.com.apirest.gym.validations;

import br.com.apirest.gym.exceptions.exercises.ExerciseTypeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ValidationExerciseType implements Validation {

    public static final List<String> EXERCISE_TYPE = Arrays.asList(
            "cardio",
            "olympic_weightlifting",
            "plyometrics",
            "powerlifting",
            "strength",
            "stretching",
            "strongman"
    );

    public void validate(String type){
        if(!EXERCISE_TYPE.contains(type)){
            throw new ExerciseTypeException("Tipo de exercício inválido: " + type);
        }
    }
}
