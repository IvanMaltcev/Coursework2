package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.EmptyQuestionOrAnswerException;
import com.sky.examquestions.exception.QuestionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> listOfQuestions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        if (question.isEmpty() || answer.isEmpty()) {
            throw new EmptyQuestionOrAnswerException("Не написан текст вопроса или ответа!");
        }
        Question javaQuestion = new Question(question, answer);
        listOfQuestions.add(javaQuestion);
        return javaQuestion;

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

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomNumberOfQuestion = random.nextInt(listOfQuestions.size());
        List<Question> questions = List.copyOf(listOfQuestions);
        return questions.get(randomNumberOfQuestion);
    }
}
