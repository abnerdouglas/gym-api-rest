package br.com.apirest.gym.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> genericException(Exception e){
        ApiError apiError = ApiError
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errors(List.of(e.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
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

//    @ExceptionHandler({EmailNotFoundException.class, NameNotFoundException.class, CpfNotFoundException.class, PasswordNotFoundException.class})
//    public ResponseEntity<ApiError> notFoundException(RuntimeException ex) {
//        ApiError apiError = ApiError
//                .builder()
//                .code(HttpStatus.NOT_FOUND.value())
//                .status(HttpStatus.NOT_FOUND.name())
//                .errors(List.of(ex.getMessage()))
//                .build();
//        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }

}
