package com.example.challengeAlkemy.exceptions;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntity extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CharacterNotExistsException.class, MovieNotExistsException.class})
    public ResponseEntity<Object> handleNotExistsExceptions(Exception ex){
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, errors);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(),apiError.getHttpStatus());
    }

    @ExceptionHandler ({EmailAlreadyRegistered.class, UsernameAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlreadyexistsExceptions(Exception ex){
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, errors);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(),apiError.getHttpStatus());
    }

    @ExceptionHandler ({GenerateTokenFailedException.class})
    public ResponseEntity<Object> handleFailedToken(Exception ex){
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(),apiError.getHttpStatus());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {

        List<String> errors = new ArrayList<>();

        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, errors);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getHttpStatus());
    }




}
