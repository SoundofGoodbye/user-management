package com.home.server.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessLogicRuntimeException {
    public ResourceNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
