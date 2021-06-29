package com.campusacademy.b2dev.backendtournamentmanagement.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.campusacademy.b2dev.backendtournamentmanagement.api.model.PlayerDTO;
import com.campusacademy.b2dev.backendtournamentmanagement.repositories.PlayerRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "players")
public class PlayersController {

    private final PlayerRepository playerRepository;

    public PlayersController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAll() {
        return ResponseEntity.ok(this.playerRepository.findAll())
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable Long id) {
        return this.playerRepository.findById(id).map(player -> ResponseEntity.ok(map(player)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
