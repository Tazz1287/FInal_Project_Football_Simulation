package com.ironhack.football_simulation.model;

import com.ironhack.football_simulation.enums.Position;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;



@Entity
public class Player extends Person {

    @Enumerated(EnumType.STRING)
    private Position position;

    private Integer jerseyNumber;
    private Integer abilityScore;

    public Player() {}

    public Player(String name,Position position, Integer jerseyNumber, Integer abilityScore) {
        super(name);
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.abilityScore = abilityScore;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public Integer getAbilityScore() {
        return abilityScore;
    }

    public void setAbilityScore(Integer abilityScore) {
        this.abilityScore = abilityScore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                ", jerseyNumber=" + jerseyNumber +
                ", abilityScore=" + abilityScore +
                '}';
    }
}
