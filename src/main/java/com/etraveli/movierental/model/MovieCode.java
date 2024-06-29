package com.etraveli.movierental.model;

import com.etraveli.movierental.exception.ErrorCode;
import com.etraveli.movierental.exception.MovieCodeNotFoundException;

public enum MovieCode {
    REGULAR("regular"),
    NEW("new"),
    CHILDREN("children");

    private final String codeValue;

    MovieCode(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeValue() {
        return this.codeValue;
    }

    public static MovieCode fromString(String code) {
        for (MovieCode movieCode : MovieCode.values()) {
            if (movieCode.getCodeValue().equals(code)) {
                return movieCode;
            }
        }
        throw new MovieCodeNotFoundException("Invalid movie code: " + code, ErrorCode.MOVIE_CODE_NOT_FOUND);
    }
}
