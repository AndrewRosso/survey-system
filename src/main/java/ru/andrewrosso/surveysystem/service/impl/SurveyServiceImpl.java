package ru.andrewrosso.surveysystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.andrewrosso.surveysystem.entity.Survey;
import ru.andrewrosso.surveysystem.repository.SurveyRepository;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {
    private final SurveyRepository surveyRepository;

    @Override
    public List<Survey> findAll() {
        if (surveyRepository.findAll()==null) {
            throw new ResourceNotFoundException();
        }
        return surveyRepository.findAll();
    }

    @Override
    public Survey findById(int id) {
        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        return surveyOptional.orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Survey add(Survey survey) {
        if (survey == null) {
            throw new NullPointerException();
        }
        return surveyRepository.save(survey);
    }

    @Override
    public void deleteById(int id) {
        surveyRepository.deleteById(id);
    }
}
