package ru.andrewrosso.surveysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.andrewrosso.surveysystem.entity.Survey;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private SurveyService surveyService;

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping()
    public List<Survey> listOfSurvey(){
        return surveyService.findAll();
    }
}