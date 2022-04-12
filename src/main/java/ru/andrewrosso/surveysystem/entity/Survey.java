package ru.andrewrosso.surveysystem.entity;

import javax.persistence.*;
import java.util.Date;

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
    private String descripton;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
}
