package com.campusacademy.b2dev.backendtournamentmanagement.repositories;

import java.util.Optional;

import com.campusacademy.b2dev.backendtournamentmanagement.model.Player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> getPlayerByName(String name);

}
