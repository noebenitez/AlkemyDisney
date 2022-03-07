package com.example.challengeAlkemy.exceptions;

public class UsernameAlreadyExistsException extends Exception{

    @Override
    public String getMessage(){
        return "Username already exists.";
    }
}
