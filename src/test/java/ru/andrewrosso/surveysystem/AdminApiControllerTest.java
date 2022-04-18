package ru.andrewrosso.surveysystem;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.andrewrosso.surveysystem.controller.AdminApiController;
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
@WebMvcTest(AdminApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AdminApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    Survey testNewSurvey = new Survey(
            3,
            "Про новый опрос",
            Date.valueOf("2022-04-17"),
            Date.valueOf("2022-06-17"),
            "тестовое описание");
    Survey testUpdateSurvey = new Survey(
            4,
            "Про измененный опрос",
            Date.valueOf("2022-04-17"),
            Date.valueOf("2022-06-17"),
            "тестовое описание");

    List<Survey> testSurveys = Arrays.asList(testSurvey1, testSurvey2);

    @Test
    public void shouldCreateMockMvc() {
        Assert.assertNotNull(mockMvc);
    }

    @Test
    public void shouldGetAllSurveys() throws Exception {

        Mockito.when(surveyService.findAll()).thenReturn(testSurveys);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/admin/surveys")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)));
    }

    @Test
    public void shouldGetStatus404WhenSurveysIsNullWhenTryGetAllSurveys() throws Exception {

        Mockito.when(surveyService.findAll()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/admin/surveys")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetSurveyById() throws Exception {
        Mockito.when(surveyService.findById(1)).thenReturn(testSurvey1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/admin/surveys/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetStatus404WhenSurveyIsNullWhenTryGetSurveyById() throws Exception {
        Mockito.when(surveyService.findById(Mockito.anyInt())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/admin/surveys/{id}", 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldAddNewSurveyAndGetStatus201() throws Exception {

        Mockito.when(surveyService.add(Mockito.any())).thenReturn(testNewSurvey);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/admin/surveys")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testNewSurvey)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Про новый опрос")));
    }

    @Test
    public void shouldUpdateSurvey() throws Exception {

        testUpdateSurvey.setId(testSurvey1.getId());

        Mockito.when(surveyService.findById(testSurvey1.getId())).thenReturn(testSurvey1);
        Mockito.when(surveyService.add(Mockito.any())).thenReturn(testUpdateSurvey);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/admin/surveys/{id}", testSurvey1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testNewSurvey)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Про измененный опрос")));
    }

    @Test
    public void shouldGetStatus404WhenUpdatableSurveyIsNull() throws Exception {

        Mockito.when(surveyService.findById(Mockito.anyInt())).thenReturn(null);
        Mockito.when(surveyService.add(Mockito.any())).thenReturn(testUpdateSurvey);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/admin/surveys/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testNewSurvey)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetStatus400WhenUpdatedContentIsNull() throws Exception {

        Mockito.when(surveyService.findById(Mockito.anyInt())).thenReturn(testSurvey1);
        Mockito.when(surveyService.add(Mockito.any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/admin/surveys/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldDeleteSurveyByIdAndGetStatus200() throws Exception {

        Mockito.when(surveyService.findById(Mockito.anyInt())).thenReturn(testSurvey1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/admin/surveys/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetStatus404WhenBeingDeletedSurveyIsNotFound() throws Exception {

        Mockito.when(surveyService.findById(Mockito.anyInt())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/admin/surveys/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
