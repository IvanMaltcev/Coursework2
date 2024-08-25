package com.sky.examquestions.repository;

import com.sky.examquestions.domain.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
