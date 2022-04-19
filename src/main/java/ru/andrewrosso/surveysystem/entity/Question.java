package ru.andrewrosso.surveysystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.andrewrosso.surveysystem.entity.enums.QuestionType;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "Type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    public Question() {
    }

    public Question(String content, QuestionType type, Survey survey) {
        this.content = content;
        this.type = type;
        this.survey = survey;
    }
}
