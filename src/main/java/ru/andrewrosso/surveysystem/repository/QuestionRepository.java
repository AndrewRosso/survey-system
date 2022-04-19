package ru.andrewrosso.surveysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.andrewrosso.surveysystem.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    //In H2DB: SELECT * FROM Question where survey_id=surveyId
    @Query("select q from Question q inner join q.survey survey where survey.id=:survey_Id")
    List<Question> findAllById(@Param("survey_Id") int surveyId);
}
