package com.etraveli.movierental.exception;

public class MovieCodeNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public MovieCodeNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
