package com.etraveli.movierental.exception;

public class MovieCodeNotFoundException extends RuntimeException {
    public MovieCodeNotFoundException(String message) {
        super(message);
    }
}
