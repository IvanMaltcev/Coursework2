package com.sky.examquestions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodDoesNotExistException extends RuntimeException {
    public MethodDoesNotExistException(String message) {
        super(message);
    }
}
