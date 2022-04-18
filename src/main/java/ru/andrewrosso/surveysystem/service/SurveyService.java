package ru.andrewrosso.surveysystem.service;

import org.springframework.stereotype.Service;
import ru.andrewrosso.surveysystem.entity.Survey;

import java.util.List;

@Service
public interface SurveyService {
    List<Survey> findAll();

    Survey findById(int id);

    Survey add(Survey survey);

    void deleteById(int id);
}
