package com.campusacademy.b2dev.backendtournamentmanagement.repositories;

import java.util.List;
import java.util.Optional;

import com.campusacademy.b2dev.backendtournamentmanagement.model.Team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> getTeamByTeamName(String name);

    List<Team> getTeamsByTeamName(String name);

}
