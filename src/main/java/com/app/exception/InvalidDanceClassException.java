package com.app.exception;

public class InvalidDanceClassException extends RuntimeException {
    public InvalidDanceClassException() {
        super("The dance class is invalid.");
    }
}
