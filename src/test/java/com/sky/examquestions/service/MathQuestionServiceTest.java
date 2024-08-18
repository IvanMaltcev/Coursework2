package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.repository.MathQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    @Mock
    MathQuestionRepository mathQuestionRepositoryMock;
    private JavaQuestionService out;

    private Question question1;
    private Question question2;
    private Question question3;

    @BeforeEach
    public void setUp() {

        out = new JavaQuestionService(mathQuestionRepositoryMock);

        question1 = new Question("QuestionText1", "QuestionAnswer1");
        question2 = new Question("QuestionText2", "QuestionAnswer2");
        question3 = new Question("QuestionText3", "QuestionAnswer3");

    }

    @Test
    public void addQuestionTesting() {

        when(mathQuestionRepositoryMock.add(question1)).thenReturn(question1);

        Question expected = new Question("QuestionText1", "QuestionAnswer1");

        assertEquals(expected, out.add(question1));
    }

    @Test
    public void removeQuestionTesting() {

        when(mathQuestionRepositoryMock.remove(question1)).thenReturn(question1);

        Question expected = new Question("QuestionText1", "QuestionAnswer1");

        assertEquals(expected, out.remove(question1));
    }

    @Test
    public void getAllQuestionTesting() {

        when(mathQuestionRepositoryMock.getAll()).thenReturn(List.of(
                question1,
                question2,
                question3
        ));

        List<Question> actual = List.copyOf(out.getAll());
        List<Question> expected = new ArrayList<>();

        expected.add(question1);
        expected.add(question2);
        expected.add(question3);

        assertEquals(expected, actual);
    }

    @Test
    public void getRandomQuestionTesting() {

        when(mathQuestionRepositoryMock.getAll()).thenReturn(List.of(
                question1,
                question2,
                question3
        ));

        Question question = out.getRandomQuestion();
        assertTrue(out.getAll().contains(question));
    }
}
