package ru.andrewrosso.surveysystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrewrosso.surveysystem.entity.Question;
import ru.andrewrosso.surveysystem.repository.QuestionRepository;
import ru.andrewrosso.surveysystem.service.QuestionService;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAll(int surveyId) {
        return questionRepository.findAllById(surveyId);
    }

    @Override
    public Question findById(int id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        return questionOptional.orElse(null);
    }

    @Override
    public Question add(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }
}
