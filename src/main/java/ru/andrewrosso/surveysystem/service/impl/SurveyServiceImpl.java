package ru.andrewrosso.surveysystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrewrosso.surveysystem.entity.Survey;
import ru.andrewrosso.surveysystem.repository.SurveyRepository;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private SurveyRepository surveyRepository;

    @Autowired
    public void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey findById(int id) {
        return surveyRepository.findById(id);
    }

    @Override
    public Survey add(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public void deleteById(int id) {
        surveyRepository.deleteById(id);
    }
}
