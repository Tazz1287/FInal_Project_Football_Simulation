package com.ironhack.football_simulation.repository;

import com.ironhack.football_simulation.model.Club;
import com.ironhack.football_simulation.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    void deleteByName(String name);

    Optional<Player> findByName(String name);

    List<Player> findByClub(Club club);

    List<Player> findByClub_Name(String clubName);
}
