package br.com.apirest.gym.exceptions;

import br.com.apirest.gym.exceptions.exercises.DifficultyLevelException;
import br.com.apirest.gym.exceptions.exercises.ExerciseTypeException;
import br.com.apirest.gym.exceptions.exercises.MuscleGroupException;
import br.com.apirest.gym.exceptions.users.InvalidTokenException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> argumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(errorList)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException ex) {
        ApiError apiError = ApiError.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED.name())
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(MuscleGroupException.class)
    public ResponseEntity<ApiError> muscleGroupValidationException(MuscleGroupException e) {
        ApiError apiError = ApiError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(e.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExerciseTypeException.class)
    public ResponseEntity<ApiError> exerciseTypeException(ExerciseTypeException e) {
        ApiError apiError = ApiError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(e.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DifficultyLevelException.class)
    public ResponseEntity<ApiError> difficultyLevelException(DifficultyLevelException e) {
        ApiError apiError = ApiError.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .errors(List.of(e.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}
