package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

