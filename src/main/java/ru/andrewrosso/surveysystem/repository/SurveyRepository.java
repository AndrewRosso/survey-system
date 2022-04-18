package ru.andrewrosso.surveysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.andrewrosso.surveysystem.entity.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey,Integer> {
    //In H2DB: SELECT * FROM BANK where id=1
    @Query("select s from Survey s where s.id=:id")
    Survey findById(@Param("id") int id);
}
