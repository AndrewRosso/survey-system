package ru.andrewrosso.surveysystem.entity;

import lombok.*;
import ru.andrewrosso.surveysystem.entity.enums.QuestionType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "QUESTION")
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "SURVEY_ID", nullable = false)
    private Survey survey;
}
