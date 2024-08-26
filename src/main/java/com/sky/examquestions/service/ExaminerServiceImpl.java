package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.AmountQuestionsException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<QuestionService> services;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService, MathQuestionService mathQuestionService) {
        this.services = List.of(javaQuestionService, mathQuestionService);
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        if (amount > services.get(0).getAll().size() * 2) {
            throw new AmountQuestionsException("Укажите меньшее количество вопросов");
        }
        Set<Question> listOfQuestionsJava = new HashSet<>();
        while (listOfQuestionsJava.size() != amount) {
            listOfQuestionsJava.add(services.get(0).getRandomQuestion());
            listOfQuestionsJava.add(services.get(1).getRandomQuestion());
        }
        return listOfQuestionsJava;
    }
}
