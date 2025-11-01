package com.ironhack.football_simulation.service;

import com.ironhack.football_simulation.model.Club;
import com.ironhack.football_simulation.model.Match;
import com.ironhack.football_simulation.model.Player;
import com.ironhack.football_simulation.repository.ClubRepository;
import com.ironhack.football_simulation.repository.MatchRepository;
import com.ironhack.football_simulation.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final Random random = new Random();

    //private Map<String, int[]> currentStandings = new HashMap<>;
    //private List<Match> currentMatches = new ArrayList<>();

    public MatchService(MatchRepository matchRepository, ClubRepository clubRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;

    }

    private int calculateTeamAbility(Club club) {
        List<Player> players = playerRepository.findByClub(club);
        if (players.isEmpty()) return 50;

        return (int) players.stream()
                .mapToInt(Player::getAbilityScore)
                .average()
                .orElse(50);
    }

    private double calculateWinNumber(int teamAbility, boolean isHome) {
        double abilityComponent = 0.30*(teamAbility / 100.00);
        double luckComponent = 0.60*(random.nextDouble());
        double homeComponent = isHome ? 0.10 : -0.10;

        return abilityComponent + luckComponent + homeComponent;
    }

    public Match simulateMatch(Match match) {
        Club home = match.getHomeClub();
        Club away = match.getAwayClub();

        int homeAbility = calculateTeamAbility(home);
        int awayAbility = calculateTeamAbility(away);

        int homeScore;
        int awayScore;

        double homeWinNumber = calculateWinNumber(homeAbility, true);
        double awayWinNumber = calculateWinNumber(awayAbility, false);

        double numberDifference = homeWinNumber - awayWinNumber;

        if(Math.abs(numberDifference) < 0.2 && random.nextDouble() < 0.6) {
            homeScore = random.nextInt(4);
            awayScore = homeScore;
        }else if(numberDifference > 0){
            homeScore = 1 + random.nextInt(4);
            awayScore = random.nextInt(3);
        }else{
            awayScore = 1 + random.nextInt(4);
            homeScore = random.nextInt(3);
        }

        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
        match.setPlayed(true);
        matchRepository.save(match);


        return match;
    }

    public Match playSingleMatch(String homeClubName, String awayClubName) {
        Club homeClub = clubRepository.findByName(homeClubName)
                .orElseThrow(() -> new RuntimeException("Club with name " + homeClubName + " not found"));
        Club awayClub = clubRepository.findByName(awayClubName)
                .orElseThrow(() -> new RuntimeException("Club with name " + awayClubName + " not found"));

        Match match = new Match(homeClub, awayClub);
        Match simulatedMatch = simulateMatch(match);
        matchRepository.save(match);


        System.out.printf("SINGLE MATCH: %-20s %d - %d %-20s%n",
                match.getHomeClub().getName(),
                match.getHomeScore(),
                match.getAwayScore(),
                match.getAwayClub().getName());

        return simulatedMatch;
    }

}
