package com.example.challengeAlkemy.exceptions;

public class EmailAlreadyRegistered extends Exception{

    @Override
    public String getMessage() {
        return "The email is already registered.";
    }
}
