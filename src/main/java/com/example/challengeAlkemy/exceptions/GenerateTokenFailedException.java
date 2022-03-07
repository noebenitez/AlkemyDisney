package com.example.challengeAlkemy.exceptions;

public class GenerateTokenFailedException extends Exception{

    @Override
    public String getMessage(){
        return "The token generation has failed";
    }
}
