package com.etraveli.movierental.exception;

public record ErrorResponse(int statusCode, String message) {
}
