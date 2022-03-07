package com.example.challengeAlkemy.exceptions;

import org.springframework.http.HttpStatus;

public class CharacterNotExistsException extends Exception{

    @Override
    public String getMessage(){
        return "The character doesn't exists.";
    }
}
