package ru.andrewrosso.surveysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/surveys")
    public List<Survey> getAllSurvey() {
        if (surveyService.findAll() == null) {
            throw new ResourceNotFoundException();
        }
        return surveyService.findAll();
    }

    @GetMapping("/surveys/{id}")
    public Survey showSurvey(@PathVariable int id) {
        if (surveyService.findById(id) == null) {
            throw new ResourceNotFoundException();
        }
        return surveyService.findById(id);
    }

    @PostMapping("/surveys")
    public ResponseEntity<Survey> addSurvey(@RequestBody Survey newSurvey) {
        Survey addedSurvey = surveyService.add(newSurvey);
        return ResponseEntity.status(201).body(addedSurvey);
    }

    @PutMapping("/surveys/{id}")
    public Survey updateSurvey(@RequestBody Survey updateSurvey,
                               @PathVariable int id) {
        if (surveyService.findById(id) == null) {
            throw new ResourceNotFoundException();
        }
        if (updateSurvey == null) {
            throw new NullPointerException();
        }
        updateSurvey.setId(id);
        return surveyService.add(updateSurvey);
    }

    @DeleteMapping("/surveys/{id}")
    public void deleteSurvey(@PathVariable int id) {
        if (surveyService.findById(id) == null) {
            throw new ResourceNotFoundException();
        }
        surveyService.deleteById(id);
    }



}
