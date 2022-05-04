package ru.andrewrosso.surveysystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SURVEY")
@Builder //- для создания объектов
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false, updatable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
