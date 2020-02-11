package com.home.server.exceptions;

import org.springframework.http.HttpStatus;

public class ConstraintException extends BusinessLogicRuntimeException {
    public ConstraintException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

    public ConstraintException(String message, HttpStatus status) {
        super(message, status);
    }
}
