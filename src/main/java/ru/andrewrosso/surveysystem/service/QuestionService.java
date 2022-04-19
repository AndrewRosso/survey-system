package ru.andrewrosso.surveysystem.service;

import org.springframework.stereotype.Service;
import ru.andrewrosso.surveysystem.entity.Question;

import java.util.List;

@Service
public interface QuestionService {
    List<Question> findAll(int surveyId);

    Question findById(int id);

    Question add(Question question);

    void deleteById(int id);
}
