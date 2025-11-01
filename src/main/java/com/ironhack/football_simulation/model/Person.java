package com.ironhack.football_simulation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToOne
    @JsonIgnore
    private Club club;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", club=" + club +
                '}';
    }
}
