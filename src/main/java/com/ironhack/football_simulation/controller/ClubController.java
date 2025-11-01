package com.ironhack.football_simulation.controller;

import com.ironhack.football_simulation.model.Club;
import com.ironhack.football_simulation.model.Coach;
import com.ironhack.football_simulation.model.Player;
import com.ironhack.football_simulation.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
class ClubController {

    private final ClubService clubService;

    ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Club createClub(@RequestBody Club club) {
        return clubService.createClub(club);
    }

    @DeleteMapping("/{clubName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClub(@PathVariable String clubName) {
        clubService.deleteClub(clubName);
    }

    @GetMapping("{clubName}/players")
    public List<Player> getClubPlayers(@PathVariable String clubName) {
        return clubService.getClubPlayers(clubName);
    }

    @GetMapping("{clubName}/coaches")
    public List<Coach> getClubCoaches(@PathVariable String clubName) {
        return clubService.getClubCoaches(clubName);
    }

    @PostMapping("/{clubName}/players/{playerName}")
    public Club addPlayerToClub(@PathVariable String clubName, @PathVariable String playerName) {
        return clubService.addPlayertoClub(clubName, playerName);
    }

    @PostMapping("/{clubName}/coaches/{coachName}")
    public Club addCoachToClub(@PathVariable String clubName, @PathVariable String coachName) {
        return clubService.addCoachtoClub(clubName, coachName);
    }







}
