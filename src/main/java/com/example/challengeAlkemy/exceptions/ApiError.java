package com.example.challengeAlkemy.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Digits;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus httpStatus;
    private List<String> errors;
}
