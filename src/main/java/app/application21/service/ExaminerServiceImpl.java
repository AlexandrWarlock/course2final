package app.application21.service;

import app.application21.dto.Question;
import app.application21.exception.NotEnoughQuestionException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random random = new Random();
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new NotEnoughQuestionException();
        }
        Set<Question> resultQuestionsSet = new HashSet<>();
        while (resultQuestionsSet.size() < amount) {
            resultQuestionsSet.add(questionService.getRandomQuestion());
        }
        return resultQuestionsSet;
    }
}
