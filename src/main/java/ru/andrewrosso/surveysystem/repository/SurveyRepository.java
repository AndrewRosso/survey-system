package ru.andrewrosso.surveysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andrewrosso.surveysystem.entity.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {
}
