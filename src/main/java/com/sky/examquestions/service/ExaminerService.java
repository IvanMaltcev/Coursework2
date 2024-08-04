package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
