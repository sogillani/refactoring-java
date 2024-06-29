package com.etraveli.movierental.model;

import com.etraveli.movierental.exception.ErrorCode;
import com.etraveli.movierental.exception.MovieCodeNotFoundException;

import java.util.Arrays;

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
        return Arrays.stream(MovieCode.values())
                .filter(movieCode -> movieCode.getCodeValue().equals(code))
                .findFirst()
                .orElseThrow(() -> new MovieCodeNotFoundException("Invalid movie code: " + code, ErrorCode.MOVIE_CODE_NOT_FOUND));
    }
}
