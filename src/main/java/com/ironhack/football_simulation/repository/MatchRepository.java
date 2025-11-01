package com.ironhack.football_simulation.repository;

import com.ironhack.football_simulation.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
