package com.app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "danceStudent")
public class DanceStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "danceClass_danceStudent",
              joinColumns = @JoinColumn(name = "danceStudent.id"),
    inverseJoinColumns = @JoinColumn(name = "danceClass.id"))
    List<DanceClass> danceClasses = new ArrayList<>();

    public DanceStudent() {

    }

    public DanceStudent(String name, String surname, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<DanceClass> getDanceClasses() {
        return danceClasses;
    }

    public void setDanceClasses(List<DanceClass> danceClasses) {
        this.danceClasses = danceClasses;
    }

    public void addDanceClass(DanceClass danceClass) {
        this.danceClasses.add(danceClass);
    }

    public void removeDanceClass(DanceClass danceClass) {
        this.danceClasses.remove(danceClass);
    }

    @Override
    public String toString() {
        return "DanceStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
