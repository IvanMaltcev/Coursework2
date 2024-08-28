package com.sky.examquestions.controller;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.MethodDoesNotExistException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/math")
public class MathQuestionController {

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        throw new MethodDoesNotExistException("Метод не существует!");
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) {
        throw new MethodDoesNotExistException("Метод не существует!");
    }

    @GetMapping
    public Collection<Question> getQuestion() {
        throw new MethodDoesNotExistException("Метод не существует!");
    }
}
