package com.ironhack.football_simulation.controller;

import com.ironhack.football_simulation.model.Match;
import com.ironhack.football_simulation.repository.MatchRepository;
import com.ironhack.football_simulation.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/run")
    public void runSeason() {
        matchService.runSeason();
    }

    @GetMapping("/results")
    public List<Map<String, Object>> getResults() {
        return matchService.getMatchResults();
    }

    @GetMapping("/standings")
    public List<Map<String, Object>> getStandings() {
        return matchService.getCurrentStandings();
    }

}
