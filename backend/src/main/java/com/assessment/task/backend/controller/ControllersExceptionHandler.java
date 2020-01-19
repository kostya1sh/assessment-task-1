package com.assessment.task.backend.controller;

import com.assessment.task.backend.exception.ControllerException;
import com.assessment.task.backend.model.rs.ErrorRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
class ControllersExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<ErrorRs> handleAllExceptions(RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorRs errorRs = ErrorRs.builder()
                .message("")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(errorRs, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ControllerException.class})
    public final ResponseEntity<ErrorRs> handleControllerException(ControllerException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        ErrorRs errorRs = ErrorRs.builder()
                .message(ex.getMessage())
                .status(ex.getStatus().value())
                .build();
        return new ResponseEntity<>(errorRs, ex.getStatus());
    }
}
