package ru.andrewrosso.surveysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.andrewrosso.surveysystem.entity.Question;
import ru.andrewrosso.surveysystem.service.QuestionService;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApiQuestionsController {

    private SurveyService surveyService;
    private QuestionService questionService;

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> getAllQuestions(@PathVariable int surveyId) {
        if (surveyService.findById(surveyId) == null) {
            throw new ResourceNotFoundException();
        }
        return questionService.findAll(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{id}")
    public Question showQuestion(@PathVariable int surveyId,
                                 @PathVariable int id) {
        if (!checkingQuestion(surveyId,id)){
            throw new ResourceNotFoundException();
        }
        return questionService.findById(id);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable int surveyId,
                                                @RequestBody Question newQuestion) {
        if (surveyService.findById(surveyId) == null) {
            throw new ResourceNotFoundException();
        }
        Question addedQuestion = questionService.add((newQuestion));
        return ResponseEntity.status(201).body(addedQuestion);
    }


    @PutMapping("/surveys/{surveyId}/questions/{id}")
    public Question updateQuestion(@PathVariable int surveyId,
                                   @PathVariable int id,
                                   @RequestBody Question updateQuestion) {
        if (!checkingQuestion(surveyId,id)){
            throw new ResourceNotFoundException();
        }
        if (updateQuestion == null) {
            throw new NullPointerException();
        }
        updateQuestion.setId(id);
        return questionService.add(updateQuestion);
    }

    @DeleteMapping("/surveys/{surveyId}/questions/{id}")
    public void deleteQuestion(@PathVariable int surveyId,
                               @PathVariable int id) {
        if (!checkingQuestion(surveyId,id)){
            throw new ResourceNotFoundException();
        }
        questionService.deleteById(id);
    }

    private Boolean checkingQuestion(int surveyId, int id) {
        Question availableQuestion = null;
        List<Question> allQuestionsBySurveyId = questionService.findAll(surveyId);

        for (Question question : allQuestionsBySurveyId) {
            if (question.equals(questionService.findById(id))) {
                availableQuestion = questionService.findById(id);
                break;
            }
        }
        return availableQuestion != null;
    }
}
