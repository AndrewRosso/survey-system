package ru.andrewrosso.surveysystem.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.andrewrosso.surveysystem.entity.Survey;
import ru.andrewrosso.surveysystem.repository.UserRepository;
import ru.andrewrosso.surveysystem.service.SurveyService;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserApiController.class)
public class UserApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SurveyService surveyService;
    @MockBean
    private UserRepository userRepository;

    Survey testSurvey1 = Survey.builder()
            .id(1)
            .name("Про учебу")
            .startDate(Date.valueOf("2022-04-17"))
            .endDate(Date.valueOf("2022-06-17"))
            .description("тестовое описание")
            .build();
    Survey testSurvey2 = Survey.builder()
            .id(2)
            .name("Про увлечения")
            .startDate(Date.valueOf("2022-04-17"))
            .endDate(Date.valueOf("2022-06-17"))
            .description("тестовое описание")
            .build();

    List<Survey> testSurveys = Arrays.asList(testSurvey1, testSurvey2);
    List<Survey> nullSurveys = null;

    @Test
    public void shouldCreateMockMvc() {
        Assert.assertNotNull(mockMvc);
    }

    @Test
    public void shouldGetAllSurveys() throws Exception {
        Mockito.when(surveyService.findAll()).thenReturn(testSurveys);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/surveys")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Про учебу")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", is("тестовое описание")));
    }

    @Test
    public void shouldGetStatus404WhenSurveysIsNull() throws Exception {
        Mockito.when(surveyService.findAll()).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/surveys")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
