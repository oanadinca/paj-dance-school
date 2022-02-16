package com.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "danceClass")
public class DanceClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    DanceType danceType;

    private Long price;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "danceClasses", fetch = FetchType.LAZY)
    private List<DanceStudent> danceStudents = new ArrayList<>();

    public DanceClass() {

    }

    public DanceClass(DanceType danceClassType, Long price, List<DanceStudent> danceStudents) {
        this.danceType = danceClassType;
        this.price = price;
        this.danceStudents = danceStudents;
    }

    public DanceType getDanceClassType() {
        return danceType;
    }

    public void setDanceClassType(DanceType danceClassType) {
        this.danceType = danceClassType;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<DanceStudent> getDanceStudents() {
        return danceStudents;
    }

    public void setDanceStudents(List<DanceStudent> danceStudents) {
        this.danceStudents = danceStudents;
    }

    public void addDanceStudent(DanceStudent danceStudent) {
        this.danceStudents.add(danceStudent);
    }

    public void removeDanceStudent(DanceStudent danceStudent) {
        this.danceStudents.remove(danceStudent);
    }
}
