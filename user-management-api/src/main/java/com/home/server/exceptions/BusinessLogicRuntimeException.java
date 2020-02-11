package com.home.server.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;


public class BusinessLogicRuntimeException extends RuntimeException {
    private String message;

    private Date timestamp;

    private HttpStatus status;

    public BusinessLogicRuntimeException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Date();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
