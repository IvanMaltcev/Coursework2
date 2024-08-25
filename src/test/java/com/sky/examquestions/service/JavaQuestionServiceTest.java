package com.sky.examquestions.service;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.repository.JavaQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    JavaQuestionRepository javaQuestionRepositoryMock;
    private JavaQuestionService out;

    private Question question1;
    private Question question2;
    private Question question3;

    @BeforeEach
    public void setUp() {

        out = new JavaQuestionService(javaQuestionRepositoryMock);

        question1 = new Question("QuestionText1", "QuestionAnswer1");
        question2 = new Question("QuestionText2", "QuestionAnswer2");
        question3 = new Question("QuestionText3", "QuestionAnswer3");

    }

    @Test
    public void addQuestionTesting() {

        when(javaQuestionRepositoryMock.add(question1)).thenReturn(question1);

        Question expected = new Question("QuestionText1", "QuestionAnswer1");

        assertEquals(expected, out.add(question1));
    }

    @Test
    public void removeQuestionTesting() {

        when(javaQuestionRepositoryMock.remove(question1)).thenReturn(question1);

        Question expected = new Question("QuestionText1", "QuestionAnswer1");

        assertEquals(expected, out.remove(question1));
    }

    @Test
    public void getAllQuestionTesting() {

        when(javaQuestionRepositoryMock.getAll()).thenReturn(List.of(
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

        when(javaQuestionRepositoryMock.getAll()).thenReturn(List.of(
                question1,
                question2,
                question3
        ));

        Question question = out.getRandomQuestion();
        assertTrue(out.getAll().contains(question));
    }
}
