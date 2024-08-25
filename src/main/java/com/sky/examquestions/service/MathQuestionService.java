package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "mathQuestionService")
public class MathQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;

    public MathQuestionService(@Qualifier("MathQuestionRepository")
                               QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        Collection<Question> mathQuestions = questionRepository.getAll();
        int randomNumberOfQuestion = random.nextInt(mathQuestions.size());
        List<Question> questions = List.copyOf(mathQuestions);
        return questions.get(randomNumberOfQuestion);
    }
}
