package com.ironhack.football_simulation.controller;

import com.ironhack.football_simulation.model.Coach;
import com.ironhack.football_simulation.model.Player;
import com.ironhack.football_simulation.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
class PersonController {

    private final PersonService personService;

    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/coaches")
    public List<Coach> getAllCoaches() {
        return personService.getAllCoaches();
    }

    @PostMapping("/coaches")
    public Coach createCoach(@RequestBody Coach coach) {
        return personService.createCoach(coach);
    }

    @DeleteMapping("/coaches/{coachName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoach(@PathVariable String coachName) {
        personService.deleteCoach(coachName);
    }

    @GetMapping("/coaches/{coachName}")
    public Coach getCoachbyName(@PathVariable String coachName) {
        return personService.getCoachByName(coachName);
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return personService.getAllPlayers();
    }

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        return personService.createPlayer(player);
    }

    @DeleteMapping("/players/{playerName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayer(@PathVariable String playerName) {
        personService.deletePlayer(playerName);
    }

    @GetMapping("/players/{playerName}")
    public Player getPlayerbyName(@PathVariable String playerName) {
        return personService.getPlayerByName(playerName);
    }

//
    @GetMapping("players/club/{clubName}")
    public ResponseEntity<List<Player>> getPlayersByClubName(@PathVariable String clubName) {
        List<Player> players = personService.getPlayersByClubName(clubName);
        return ResponseEntity.ok(players);
    }
}