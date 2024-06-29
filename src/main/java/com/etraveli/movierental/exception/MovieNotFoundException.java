package com.etraveli.movierental.exception;

public class MovieNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public MovieNotFoundException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
