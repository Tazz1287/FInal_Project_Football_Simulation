package com.ironhack.football_simulation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List <Player> players = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List <Coach> coaches = new ArrayList<>();

    public Club() {
    }

    public Club(String name) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                ", coaches=" + coaches +
                '}';
    }
}
