package com.sky.examquestions.repository;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.EmptyQuestionOrAnswerException;
import com.sky.examquestions.exception.QuestionNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository(value = "javaQuestionRepository")
public class JavaQuestionRepository implements QuestionRepository {

    private final Set<Question> listOfQuestions = new HashSet<>();

    @PostConstruct
    private void init() {
        listOfQuestions.add(new Question("JavaQuestionText1", "JavaQuestionAnswer1"));
        listOfQuestions.add(new Question("JavaQuestionText2", "JavaQuestionAnswer2"));
        listOfQuestions.add(new Question("JavaQuestionText3", "JavaQuestionAnswer3"));
        listOfQuestions.add(new Question("JavaQuestionText4", "JavaQuestionAnswer4"));
        listOfQuestions.add(new Question("JavaQuestionText5", "JavaQuestionAnswer5"));

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
