package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "javaQuestionService")
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository javaQuestionRepository;

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
        this.javaQuestionRepository = questionRepository;
    }

    @Override
    public Question add(Question question) {
        return javaQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return javaQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        Collection<Question> javaQuestions = javaQuestionRepository.getAll();
        int randomNumberOfQuestion = random.nextInt(javaQuestions.size());
        List<Question> questions = List.copyOf(javaQuestions);
        return questions.get(randomNumberOfQuestion);
    }
}
