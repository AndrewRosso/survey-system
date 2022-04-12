package ru.andrewrosso.surveysystem.service;

import org.springframework.stereotype.Service;
import ru.andrewrosso.surveysystem.entity.Survey;

import java.util.List;

@Service
public interface SurveyService {
    List<Survey> findAll();
    Survey save(Survey survey);
    void delete(int id);
}
