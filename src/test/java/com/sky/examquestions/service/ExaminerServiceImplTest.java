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
    JavaQuestionService javaQuestionServiceMock;
    @Mock
    MathQuestionService mathQuestionServiceMock;

    private ExaminerService examinerService;

    private Question javaQuestion1;
    private Question javaQuestion2;
    private Question javaQuestion3;
    private Question mathQuestion1;
    private Question mathQuestion2;
    private Question mathQuestion3;


    @BeforeEach
    public void setUp() {

        examinerService = new ExaminerServiceImpl(List.of(javaQuestionServiceMock, mathQuestionServiceMock));

        javaQuestion1 = new Question("javaQuestionText1", "javaQuestionAnswer1");
        javaQuestion2 = new Question("javaQuestionText2", "javaQuestionAnswer2");
        javaQuestion3 = new Question("javaQuestionText3", "javaQuestionAnswer3");

        mathQuestion1 = new Question("269 - 145", "124");
        mathQuestion2 = new Question("85 * 2", "170");
        mathQuestion3 = new Question("643 + 182", "825");

        when(javaQuestionServiceMock.getAll()).thenReturn(List.of(
                javaQuestion1,
                javaQuestion2,
                javaQuestion3
        ));

    }

    @Test
    public void getQuestionsTesting() {
        when(javaQuestionServiceMock.getRandomQuestion())
                .thenReturn(javaQuestion1)
                .thenReturn(javaQuestion1)
                .thenReturn(javaQuestion3)
                .thenReturn(javaQuestion1)
                .thenReturn(javaQuestion3)
                .thenReturn(javaQuestion2);
        when(mathQuestionServiceMock.getRandomQuestion())
                .thenReturn(mathQuestion1)
                .thenReturn(mathQuestion2)
                .thenReturn(mathQuestion3);

        List<Question> actual = List.copyOf(examinerService.getQuestions(6));

        List<Question> expected = new ArrayList<>();

        expected.add(mathQuestion3);
        expected.add(mathQuestion1);
        expected.add(mathQuestion2);
        expected.add(javaQuestion1);
        expected.add(javaQuestion3);
        expected.add(javaQuestion2);

        assertEquals(expected, actual);
        verify(javaQuestionServiceMock, times(6)).getRandomQuestion();
        verify(mathQuestionServiceMock, times(6)).getRandomQuestion();

    }

    @Test
    public void exceptionAmountQuestionTesting() {

        assertThrows(AmountQuestionsException.class,
                () -> examinerService.getQuestions(7));
    }

}
