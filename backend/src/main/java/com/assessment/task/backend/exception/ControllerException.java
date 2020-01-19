package com.assessment.task.backend.exception;

import org.springframework.http.HttpStatus;

public abstract class ControllerException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public ControllerException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
