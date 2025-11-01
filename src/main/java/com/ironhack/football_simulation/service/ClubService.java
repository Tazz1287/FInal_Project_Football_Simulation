package com.ironhack.football_simulation.service;

import com.ironhack.football_simulation.model.Club;
import com.ironhack.football_simulation.model.Coach;
import com.ironhack.football_simulation.model.Player;
import com.ironhack.football_simulation.repository.ClubRepository;
import com.ironhack.football_simulation.repository.CoachRepository;
import com.ironhack.football_simulation.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final CoachRepository coachRepository;

    ClubService(ClubRepository clubRepository, PlayerRepository playerRepository, CoachRepository coachRepository) {
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
        this.coachRepository = coachRepository;
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public void deleteClub(String clubName) {
        clubRepository.deleteByName(clubName);
    }

    public List<Player> getClubPlayers(String clubName) {
        Optional<Club> club = clubRepository.findByName(clubName);
        if(club.isPresent()) {
            return club.get().getPlayers();
        }else{
            return new ArrayList<>();
        }
    }

    public List<Coach> getClubCoaches(String clubName) {
        Optional<Club> club = clubRepository.findByName(clubName);
        if(club.isPresent()) {
            return club.get().getCoaches();
        }else{
            return new ArrayList<>();
        }
    }

    public Club addPlayertoClub(String clubName, String playerName) {
        Club club = clubRepository.findByName(clubName)
                .orElseThrow(()-> new RuntimeException("Club not found"));
        Player player = playerRepository.findByName(playerName)
                .orElseThrow(()-> new RuntimeException("Player not found"));

        club.getPlayers().add(player);
        player.setClub(club);
        return clubRepository.save(club);

    }

    public Club addCoachtoClub(String clubName, String coachName) {
        Club club = clubRepository.findByName(clubName)
                .orElseThrow(()-> new RuntimeException("Club not found"));
        Coach coach =coachRepository.findByName(coachName)
                .orElseThrow(()-> new RuntimeException("Coach not found"));

        club.getCoaches().add(coach);
        coach.setClub(club);
        return clubRepository.save(club);

    }

}
