package com.sky.examquestions.repository;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.EmptyQuestionOrAnswerException;
import com.sky.examquestions.exception.QuestionNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository(value = "MathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> listOfQuestions = new HashSet<>();

    @PostConstruct
    private void init() {
        listOfQuestions.add(new Question("MathQuestionText1", "MathQuestionAnswer1"));
        listOfQuestions.add(new Question("MathQuestionText2", "MathQuestionAnswer2"));
        listOfQuestions.add(new Question("MathQuestionText3", "MathQuestionAnswer3"));
        listOfQuestions.add(new Question("MathQuestionText4", "MathQuestionAnswer4"));
        listOfQuestions.add(new Question("MathQuestionText5", "MathQuestionAnswer5"));

    }

    @Override
    public Question add(Question question) {
        if (question.getQuestion().isEmpty() || question.getAnswer().isEmpty()) {
            throw new EmptyQuestionOrAnswerException("Не написан текст вопроса или ответа!");
        }
        listOfQuestions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!listOfQuestions.contains(question)) {
            throw new QuestionNotFoundException("Такого вопроса нет!");
        } else {
            listOfQuestions.remove(question);
            return question;
        }
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(listOfQuestions);
    }
}
