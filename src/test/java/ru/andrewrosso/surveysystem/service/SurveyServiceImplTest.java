package ru.andrewrosso.surveysystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import ru.andrewrosso.surveysystem.repository.SurveyRepository;
import ru.andrewrosso.surveysystem.service.impl.SurveyServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SurveyServiceImplTest {
    @Mock
    private SurveyRepository surveyRepository;
    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Test(expected = ResourceNotFoundException.class)
    public void shouldReturnNotFoundExcWhenSurveysIsNullWhenTryGetAllSurvey() {
        Mockito.when(surveyRepository.findAll()).thenReturn(null);

        surveyService.findAll();
    }

    @Test(expected = NullPointerException.class)
    public void shouldReturnNullPointerExcWhenSurveyIsNullWhenTryUpdateSurvey() {
        surveyService.add(null);
    }
}
