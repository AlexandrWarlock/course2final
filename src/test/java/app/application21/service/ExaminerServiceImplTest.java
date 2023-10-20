package app.application21.service;

import app.application21.dto.Question;
import app.application21.exception.NotEnoughQuestionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    QuestionService questionService;
    @InjectMocks
    ExaminerServiceImpl underTest;

    @Test
    void getQuestions_shouldThrowExceptionWhenNotEnoughQuestion() {
        when(questionService.getAll()).thenReturn(Collections.emptySet());
        assertThrows(NotEnoughQuestionException.class,
                () -> underTest.getQuestions(1));
    }

    @Test
    void getQuestions_shouldReturnUnickQuestionsCollection() {
        int amount = 2;
        Question question1 = new Question("q1", "a1");
        Question question2 = new Question("q2", "a2");

        Set<Question> questions = Set.of(question1, question2);

        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(question1, question2);
        Collection<Question> result = underTest.getQuestions(amount);
        assertEquals(questions, result);
        assertEquals(amount, result.stream().distinct().count());
    }
}