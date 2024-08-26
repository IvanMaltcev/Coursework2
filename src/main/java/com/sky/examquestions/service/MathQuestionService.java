package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "mathQuestionService")
public class MathQuestionService implements QuestionService {

    @Override
    public Question add(Question question) {
        return null;
    }

    @Override
    public Question remove(Question question) {
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return null;
    }

    @Override
    public Question getRandomQuestion() {
        String[] operators = {"+", "-", "*", "/"};
        Random random = new Random();
        int randomNumber1 = random.nextInt(1000);
        int randomNumber2 = random.nextInt(1000) + 1;
        int randomOperator = random.nextInt(operators.length);
        String operator = operators[randomOperator];
        String questionText = randomNumber1 + operator + randomNumber2;
        int result = calculationResult(randomNumber1, randomNumber2, operator);
        String questionAnswer = String.valueOf(result);
        return new Question(questionText, questionAnswer);

    }

    private int calculationResult(int a, int b, String operator) {
        return switch (operator) {
            case ("+") -> a + b;
            case ("-") -> a - b;
            case ("*") -> a * b;
            case ("/") -> a / b;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }
}
