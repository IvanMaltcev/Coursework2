package com.sky.examquestions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyQuestionOrAnswerException extends RuntimeException {
    public EmptyQuestionOrAnswerException(String message) {
        super(message);
    }
}
