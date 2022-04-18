package ru.andrewrosso.surveysystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "SURVEY")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Name", length = 64, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "Start_Date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "End_Date", nullable = false)
    private Date endDate;

    @Column(name = "Description", nullable = false)
    private String description;

    public Survey() {
    }

    public Survey(String name, Date startDate, Date endDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
}
