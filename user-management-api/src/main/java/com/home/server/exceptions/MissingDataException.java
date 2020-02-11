package com.home.server.exceptions;

import org.springframework.http.HttpStatus;

public class MissingDataException extends BusinessLogicRuntimeException {
    public MissingDataException(String message, HttpStatus status) {
        super(message, status);
    }
}
