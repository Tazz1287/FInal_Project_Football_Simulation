package com.ironhack.football_simulation.model;


import com.ironhack.football_simulation.enums.CoachPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Coach extends Person {

    @Enumerated(EnumType.STRING)
    private CoachPosition position;

    public Coach() {
    }

    public Coach(String name,CoachPosition position) {
        super(name);
        this.position = position;
    }

    public CoachPosition getPosition() {
        return position;
    }

    public void setPosition(CoachPosition position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "position=" + position +
                '}';
    }
}
