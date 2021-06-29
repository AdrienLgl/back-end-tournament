package com.campusacademy.b2dev.backendtournamentmanagement.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.campusacademy.b2dev.backendtournamentmanagement.api.model.PlayerDTO;
import com.campusacademy.b2dev.backendtournamentmanagement.model.Player;
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
    public ResponseEntity<List<Player>> getAll() {
        List<Player> player = this.playerRepository.findAll();
        return ResponseEntity.ok(player);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable Long id) {
        return this.playerRepository.findById(id).map(player -> ResponseEntity.ok(map(player)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> create(@RequestBody PlayerDTO playerDTO) {
        Player player = new Player();
        player.setPlayerName(playerDTO.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(map(this.playerRepository.save(player)));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Optional<Player> playerOptional = this.playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Player playerToUpdate = playerOptional.get();
        playerToUpdate.setPlayerName(playerDTO.getName());
        return ResponseEntity.ok(map(this.playerRepository.save(playerToUpdate)));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.playerRepository.deleteById(id);
    }

    private PlayerDTO map(Player player) {
        return new PlayerDTO(player.getId(), player.getPlayerName());
    }

}
