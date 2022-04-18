package ru.andrewrosso.surveysystem;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.andrewrosso.surveysystem.controller.UserApiController;
import ru.andrewrosso.surveysystem.entity.Survey;
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

    Survey testSurvey1 = new Survey(
            1,
            "Про учебу",
            Date.valueOf("2022-04-17"),
            Date.valueOf("2022-06-17"),
            "тестовое описание");
    Survey testSurvey2 = new Survey(
            2,
            "Про увлечения",
            Date.valueOf("2022-04-17"),
            Date.valueOf("2022-06-17"),
            "тестовое описание");

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

        Mockito.when(surveyService.findAll()).thenReturn(nullSurveys);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/surveys")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
