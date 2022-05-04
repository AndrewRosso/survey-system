package ru.andrewrosso.surveysystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andrewrosso.surveysystem.entity.Survey;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiController {
    private final SurveyService surveyService;

    @GetMapping("/surveys")
    public List<Survey> getAllSurvey() {
        return surveyService.findAll();
    }

    @GetMapping("/surveys/{id}")
    public Survey showSurvey(@PathVariable int id) {
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
        surveyService.findById(id);
        updateSurvey.setId(id);
        return surveyService.add(updateSurvey);
    }

    @DeleteMapping("/surveys/{id}")
    public void deleteSurvey(@PathVariable int id) {
        surveyService.findById(id);
        surveyService.deleteById(id);
    }
}
