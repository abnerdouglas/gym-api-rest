package br.com.apirest.gym.validations;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

@Component
public class ValidationParameter implements Validation{

    private static final List<String> PARAMETER_TYPES = Arrays.asList(
            "muscle",
            "type",
            "difficulty"
    );

    public void validate(String parameter){
        if (!PARAMETER_TYPES.contains(parameter)){
            throw new InvalidParameterException("Parametro inválido: " + parameter);
        }
    }
}
