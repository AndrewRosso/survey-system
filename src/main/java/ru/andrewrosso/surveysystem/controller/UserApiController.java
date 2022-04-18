package ru.andrewrosso.surveysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.andrewrosso.surveysystem.entity.Survey;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class UserApiController {

    private SurveyService surveyService;

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping()
    public List<Survey> getAllSurvey() {
        if (surveyService.findAll() == null) {
            throw new ResourceNotFoundException();
        }
        return surveyService.findAll();
    }
}
