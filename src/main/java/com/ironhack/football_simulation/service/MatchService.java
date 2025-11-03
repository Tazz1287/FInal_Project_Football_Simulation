package com.ironhack.football_simulation.service;

import com.ironhack.football_simulation.model.Club;
import com.ironhack.football_simulation.model.Match;
import com.ironhack.football_simulation.model.Player;
import com.ironhack.football_simulation.model.Standings;
import com.ironhack.football_simulation.repository.ClubRepository;
import com.ironhack.football_simulation.repository.MatchRepository;
import com.ironhack.football_simulation.repository.PlayerRepository;
import com.ironhack.football_simulation.repository.StandingsRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final StandingsRepository standingsRepository;
    private final Random random = new Random();

    public MatchService(MatchRepository matchRepository, ClubRepository clubRepository, PlayerRepository playerRepository, StandingsRepository standingsRepository) {
        this.matchRepository = matchRepository;
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
        this.standingsRepository = standingsRepository;
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

    private void updateStandings(String team, int goalsFor, int goalsAgainst) {
        Standings standing = standingsRepository.findByClubName(team)
                .orElse(new Standings(team));

        standing.setPlayed(standing.getPlayed() + 1);
        standing.setGoalsFor(standing.getGoalsFor() + goalsFor);
        standing.setGoalsAgainst(standing.getGoalsAgainst() + goalsAgainst);

        if (goalsFor > goalsAgainst) {
            standing.setWins(standing.getWins() + 1);
            standing.setPoints(standing.getPoints() + 3);
        } else if (goalsFor == goalsAgainst) {
            standing.setDraws(standing.getDraws() + 1);
            standing.setPoints(standing.getPoints() + 1);
        } else {
            standing.setLosses(standing.getLosses() + 1);
        }

        standingsRepository.save(standing);
    }

    public void runSeason() {
        standingsRepository.deleteAll();
        matchRepository.deleteAll();

        System.out.println("🚀 STARTING FOOTBALL SEASON");
        System.out.println("============================");

        List<Club> clubs = clubRepository.findAll();

        for (Club club : clubs) {
            standingsRepository.save(new Standings(club.getName()));
        }

        List<Match> seasonMatches = new ArrayList<>();

        for (int i = 0; i < clubs.size(); i++) {
            for (int j = i + 1; j < clubs.size(); j++) {
                Match homeMatch = new Match(clubs.get(i), clubs.get(j));
                seasonMatches.add(homeMatch);

                Match awayMatch = new Match(clubs.get(j), clubs.get(i));
                seasonMatches.add(awayMatch);
            }
        }
        matchRepository.saveAll(seasonMatches);

        System.out.println("\n⚽ PLAYING MATCHES");
        System.out.println("==================");

        for (Match match : seasonMatches) {
            simulateMatch(match);
            updateStandings(match.getHomeClub().getName(), match.getHomeScore(), match.getAwayScore());
            updateStandings(match.getAwayClub().getName(), match.getAwayScore(), match.getHomeScore());

            System.out.printf("⚽ %-20s %d - %d %-20s%n",
                    match.getHomeClub().getName(),
                    match.getHomeScore(),
                    match.getAwayScore(),
                    match.getAwayClub().getName());
        }

        printStandings();
    }

    private void printStandings() {
        List<Standings> standings = standingsRepository.findAllByOrderByPointsDesc();

        System.out.println("\n🏆 FINAL LEAGUE STANDINGS");
        System.out.println("===========================================");
        System.out.printf("%-20s %2s %2s %2s %2s %2s %2s %2s%n",
                "Club", "P", "W", "D", "L", "GF", "GA", "Pts");

        for (Standings standing : standings) {
            System.out.printf("%-20s %2d %2d %2d %2d %2d %2d %2d%n",
                    standing.getClubName(),
                    standing.getPlayed(),
                    standing.getWins(),
                    standing.getDraws(),
                    standing.getLosses(),
                    standing.getGoalsFor(),
                    standing.getGoalsAgainst(),
                    standing.getPoints());
        }
        System.out.println("===========================================");
    }

    public List<Map<String, Object>> getMatchResults() {
        List<Match> allMatches = matchRepository.findAll();

        return allMatches.stream()
                .map(match -> {
                    Map<String, Object> matchResult = new HashMap<>();
                    matchResult.put("id", match.getId());
                    matchResult.put("homeClub", match.getHomeClub().getName());
                    matchResult.put("awayClub", match.getAwayClub().getName());
                    matchResult.put("homeScore", match.getHomeScore());
                    matchResult.put("awayScore", match.getAwayScore());
                    matchResult.put("played", match.getPlayed());
                    matchResult.put("result", match.getResult());
                    return matchResult;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getCurrentStandings() {
        List<Standings> standings = standingsRepository.findAllByOrderByPointsDesc();

        return standings.stream()
                .map(standing -> {
                    Map<String, Object> standingMap = new HashMap<>();
                    standingMap.put("club", standing.getClubName());
                    standingMap.put("played", standing.getPlayed());
                    standingMap.put("wins", standing.getWins());
                    standingMap.put("draws", standing.getDraws());
                    standingMap.put("losses", standing.getLosses());
                    standingMap.put("goalsFor", standing.getGoalsFor());
                    standingMap.put("goalsAgainst", standing.getGoalsAgainst());
                    standingMap.put("points", standing.getPoints());
                    standingMap.put("goalDifference", standing.getGoalsFor() - standing.getGoalsAgainst());
                    return standingMap;
                })
                .collect(Collectors.toList());
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }
}