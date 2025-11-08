package com.ironhack.football_simulation.service;

import com.ironhack.football_simulation.model.Coach;
import com.ironhack.football_simulation.model.Player;
import com.ironhack.football_simulation.repository.CoachRepository;
import com.ironhack.football_simulation.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PlayerRepository playerRepository;
    private final CoachRepository coachRepository;

    public PersonService(PlayerRepository playerRepository, CoachRepository coachRepository) {
        this.playerRepository = playerRepository;
        this.coachRepository = coachRepository;
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach createCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public void deleteCoach(String coachName) {
        coachRepository.deleteByName(coachName);
    }

    public Coach getCoachByName(String coachName) {
        return coachRepository.findByName(coachName)
                .orElseThrow(() -> new RuntimeException("Coach with name " + coachName + " not found"));
    }


    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(String playerName) {
        playerRepository.deleteByName(playerName);
    }

    public List<Player> getPlayersByClubName(String clubName) {
        return playerRepository.findByClub_Name(clubName);
    }

    public Player getPlayerByName(String playerName) {
        return playerRepository.findByName(playerName).orElseThrow(()-> new RuntimeException("Player with name " + playerName + " not found"));
    }
}
