package com.sky.examquestions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmountQuestionsException extends RuntimeException {
    public AmountQuestionsException(String message) {
        super(message);
    }
}
