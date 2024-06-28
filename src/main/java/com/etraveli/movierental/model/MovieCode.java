package com.etraveli.movierental.model;

public enum MovieCode {
    REGULAR("regular"),
    NEW("new"),
    CHILDREN("children");

    private final String codeValue;

    private MovieCode(String codeValue) {
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
        throw new IllegalArgumentException("Invalid movie code: " + code);
    }
}
