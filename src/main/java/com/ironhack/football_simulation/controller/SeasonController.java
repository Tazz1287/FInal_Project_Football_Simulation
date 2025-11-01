package com.ironhack.football_simulation.controller;

import com.ironhack.football_simulation.model.Match;
import com.ironhack.football_simulation.repository.MatchRepository;
import com.ironhack.football_simulation.service.MatchService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/season")
class SeasonController {

    private final MatchService matchService;

    public SeasonController(MatchService matchService){
        this.matchService = matchService;
    }

    @PostMapping("play-match/{homeTeamName}/{awayTeamName}")
    public Match playSingleMatch(@PathVariable String homeTeamName, @PathVariable String awayTeamName) {
        return matchService.playSingleMatch(homeTeamName, awayTeamName);

    }

}
