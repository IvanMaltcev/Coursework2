package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.AmountQuestionsException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new AmountQuestionsException("Укажите меньшее количество вопросов");
        }
        Set<Question> listOfQuestionsJava = new HashSet<>();
        while (listOfQuestionsJava.size() < amount) {
            listOfQuestionsJava.add(questionService.getRandomQuestion());
        }
        return listOfQuestionsJava;
    }
}
