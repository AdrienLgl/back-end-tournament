package com.campusacademy.b2dev.backendtournamentmanagement.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import com.campusacademy.b2dev.backendtournamentmanagement.api.model.TeamDTO;
import com.campusacademy.b2dev.backendtournamentmanagement.repositories.TeamRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "teams")
public class TeamsController {

    private final TeamRepository teamRepository;

    public TeamsController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        return ResponseEntity.ok(this.teamRepository.findAll().stream().map(this::map).collect(Collectors.toList()));
    }

}
