package com.sky.examquestions.repository;

import com.sky.examquestions.domain.Question;
import com.sky.examquestions.exception.EmptyQuestionOrAnswerException;
import com.sky.examquestions.exception.QuestionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionRepositoryTest {
    private JavaQuestionRepository out;

    private Question question1;

    @BeforeEach
    public void setUp() {

        out = new JavaQuestionRepository();

        question1 = out.add(new Question("QuestionText1", "QuestionAnswer1"));
    }

    @Test
    public void addQuestionTesting() {

        Question expected = new Question("QuestionText1", "QuestionAnswer1");

        assertEquals(expected, question1);
        assertEquals(1, out.getAll().size());
    }

    @Test
    public void addOneQuestionWithAnswerTwiceTesting() {

        out.add(new Question("QuestionText1", "QuestionAnswer1"));

        assertEquals(1, out.getAll().size());
    }

    @ParameterizedTest
    @MethodSource("EmptyQuestionOrAnswerForTests")
    public void exceptionEmptyQuestionOrAnswerTextTesting(String question, String answer) {

        Exception exception = assertThrows(EmptyQuestionOrAnswerException.class,
                () -> out.add(new Question(question, answer)));

        assertEquals("Не написан текст вопроса или ответа!", exception.getMessage());

    }

    @ParameterizedTest
    @MethodSource("EmptyQuestionForTests")
    public void exceptionEmptyQuestionOrAnswerTextTesting(Question question) {

        Exception exception = assertThrows(EmptyQuestionOrAnswerException.class,
                () -> out.add(question));

        assertEquals("Не написан текст вопроса или ответа!", exception.getMessage());

    }

    @Test
    public void removeQuestionTesting() {

        assertEquals(question1, out.remove(question1));
        assertEquals(0, out.getAll().size());
    }

    @Test
    public void exceptionRemoveNotExistingQuestionTesting() {

        Question question = new Question("QuestionText", "QuestionAnswer");
        Exception exception = assertThrows(QuestionNotFoundException.class,
                () -> out.remove(question));

        assertEquals("Такого вопроса нет!", exception.getMessage());
        assertEquals(1, out.getAll().size());
    }

    @Test
    public void getAllQuestionTesting() {

        List<Question> actual = List.copyOf(out.getAll());
        List<Question> expected = new ArrayList<>();

        expected.add(question1);

        assertEquals(expected, actual);
    }

    public static Stream<Arguments> EmptyQuestionOrAnswerForTests() {
        return Stream.of(
                Arguments.of("", "QuestionAnswer"),
                Arguments.of("QuestionText", ""),
                Arguments.of("", "")
        );
    }

    public static Stream<Arguments> EmptyQuestionForTests() {
        return Stream.of(
                Arguments.of(new Question("", "QuestionAnswer")),
                Arguments.of(new Question("QuestionText", "")),
                Arguments.of(new Question("", ""))
        );
    }
}
