package com.example.challengeAlkemy.exceptions;

public class MovieNotExistsException extends Exception{

    @Override
    public String getMessage(){
        return "The movie doesn't exists.";
    }
}
