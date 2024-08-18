package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.AmountQuestionsException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;

    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("JavaQuestionService")
                               QuestionService javaQuestionService,
                               @Qualifier("MathQuestionService")
                               QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            throw new AmountQuestionsException("Укажите меньшее количество вопросов");
        }
        Set<Question> listOfQuestionsJava = new HashSet<>();
        while (listOfQuestionsJava.size() < amount) {
            listOfQuestionsJava.add(javaQuestionService.getRandomQuestion());
            listOfQuestionsJava.add(mathQuestionService.getRandomQuestion());
        }
        return listOfQuestionsJava;
    }
}
