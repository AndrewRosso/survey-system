package ru.andrewrosso.surveysystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andrewrosso.surveysystem.entity.Question;
import ru.andrewrosso.surveysystem.service.QuestionService;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApiQuestionsController {
    private final SurveyService surveyService;
    private final QuestionService questionService;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> getAllQuestions(@PathVariable int surveyId) {
        surveyService.findById(surveyId);
        return questionService.findAll(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{id}")
    public Question showQuestion(@PathVariable int surveyId,
                                 @PathVariable int id) {
        return questionService.findById(surveyId,id);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable int surveyId,
                                                @RequestBody Question newQuestion) {
        surveyService.findById(surveyId);
        Question addedQuestion = questionService.add((newQuestion));
        return ResponseEntity.status(201).body(addedQuestion);
    }

    @PutMapping("/surveys/{surveyId}/questions/{id}")
    public Question updateQuestion(@PathVariable int surveyId,
                                   @PathVariable int id,
                                   @RequestBody Question updateQuestion) {
        questionService.findById(surveyId, id);
        updateQuestion.setId(id);
        return questionService.add(updateQuestion);
    }

    @DeleteMapping("/surveys/{surveyId}/questions/{id}")
    public void deleteQuestion(@PathVariable int surveyId,
                               @PathVariable int id) {
        questionService.findById(surveyId,id);
        questionService.deleteById(id);
    }
}
