package com.home.server.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationFailedException extends BusinessLogicRuntimeException {

    public ValidationFailedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public ValidationFailedException(String message, HttpStatus status) {
        super(message, status);
    }
}
