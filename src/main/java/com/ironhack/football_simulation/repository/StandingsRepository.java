package com.ironhack.football_simulation.repository;

import com.ironhack.football_simulation.model.Standings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StandingsRepository extends JpaRepository<Standings, Long> {
    Optional<Standings> findByClubName(String clubName);
    List<Standings> findAllByOrderByPointsDesc();
    void deleteAll();
}
