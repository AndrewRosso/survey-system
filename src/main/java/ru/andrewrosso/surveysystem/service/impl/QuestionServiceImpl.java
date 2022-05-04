package ru.andrewrosso.surveysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.andrewrosso.surveysystem.entity.Question;
import ru.andrewrosso.surveysystem.repository.QuestionRepository;
import ru.andrewrosso.surveysystem.repository.SurveyRepository;
import ru.andrewrosso.surveysystem.service.QuestionService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> findAll(int surveyId) {
        return questionRepository.findQuestionBySurveyId(surveyId);
    }

    @Override
    public Question findById(int surveyId, int questionId) {
        if (!checkingQuestion(surveyId, questionId)) {
            throw new ResourceNotFoundException();
        }
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        return questionOptional.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new NullPointerException();
        }
        return questionRepository.save(question);
    }

    @Override
    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

    private Boolean checkingQuestion(int surveyId, int questionId) {
        Question availableQuestion = null;
        List<Question> allQuestionsBySurveyId = questionRepository.findQuestionBySurveyId(surveyId);

        for (Question question : allQuestionsBySurveyId) {
            if (question.equals(questionRepository.findById(questionId).get())) {
                availableQuestion = questionRepository.findById(questionId).get();
                break;
            }
        }
        return availableQuestion != null;
    }
}
