package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.AmountQuestionsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    QuestionService questionServiceMock;

    private ExaminerService examinerService;

    private Question question;
    private Question question1;
    private Question question2;

    @BeforeEach
    public void setUp() {

        examinerService = new ExaminerServiceImpl(questionServiceMock);

        question = new Question("QuestionText", "QuestionAnswer");
        question1 = new Question("QuestionText1", "QuestionAnswer1");
        question2 = new Question("QuestionText2", "QuestionAnswer2");

        when(questionServiceMock.getAll()).thenReturn(List.of(
                question,
                question1,
                question2
        ));
    }

    @Test
    public void getQuestionsTesting() {
        when(questionServiceMock.getRandomQuestion())
                .thenReturn(question)
                .thenReturn(question)
                .thenReturn(question2)
                .thenReturn(question)
                .thenReturn(question2)
                .thenReturn(question1);

        List<Question> actual = List.copyOf(examinerService.getQuestions(3));

        List<Question> expected = new ArrayList<>();

        expected.add(question2);
        expected.add(question1);
        expected.add(question);

        assertEquals(expected, actual);
        verify(questionServiceMock, times(6)).getRandomQuestion();

    }

    @Test
    public void exceptionAmountQuestionTesting() {

        assertThrows(AmountQuestionsException.class,
                () -> examinerService.getQuestions(4));
    }

}
