package com.ironhack.football_simulation.repository;

import com.ironhack.football_simulation.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByName(String name);

    void deleteByName(String name);


}
