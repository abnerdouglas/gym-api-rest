package br.com.apirest.gym.validations;

import br.com.apirest.gym.exceptions.DifficultyLevelException;

import java.util.Arrays;
import java.util.List;

public class ValidationDifficulty implements Validation{

    private static final List<String> DIFFICULTY_LEVELS = Arrays.asList(
            "beginner",
            "intermediate",
            "expert"
    );

    public void validate(String difficulty) {
        if (!DIFFICULTY_LEVELS.contains(difficulty)) {
            throw new DifficultyLevelException("Nível de dificuldade inválido: " + difficulty);
        }
    }
}
