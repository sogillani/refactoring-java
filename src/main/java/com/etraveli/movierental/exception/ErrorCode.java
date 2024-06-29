package com.etraveli.movierental.exception;

public enum ErrorCode {
    MOVIE_ID_NOT_FOUND(1001),
    MOVIE_CODE_NOT_FOUND(1002),
    ID_NOT_FOUND(1003);

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
