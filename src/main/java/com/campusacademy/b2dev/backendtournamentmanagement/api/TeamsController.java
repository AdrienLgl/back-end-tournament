package com.campusacademy.b2dev.backendtournamentmanagement.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.campusacademy.b2dev.backendtournamentmanagement.api.model.GameDTO;
import com.campusacademy.b2dev.backendtournamentmanagement.api.model.PlayerDTO;
import com.campusacademy.b2dev.backendtournamentmanagement.api.model.TeamDTO;
import com.campusacademy.b2dev.backendtournamentmanagement.model.Game;
import com.campusacademy.b2dev.backendtournamentmanagement.model.Player;
import com.campusacademy.b2dev.backendtournamentmanagement.model.Team;
import com.campusacademy.b2dev.backendtournamentmanagement.repositories.TeamRepository;
import org.springframework.http.HttpStatus;

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

    @GetMapping(path = "{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable Long id) {
        return this.teamRepository.findById(id).map(superHero -> ResponseEntity.ok(map(superHero)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDTO> create(@RequestBody TeamDTO teamDTO) {
        Team team = map(teamDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(map(this.teamRepository.save(team)));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        Optional<Team> teamOptional = this.teamRepository.findById(id);
        if (teamOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Team teamToUpdate = map(teamDTO);
        return ResponseEntity.ok(map(this.teamRepository.save(teamToUpdate)));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.teamRepository.deleteById(id);
    }

    private TeamDTO map(Team team) {
        return new TeamDTO(team.getId(), team.getTeamName(), mapPlayer(team.getPlayers()));
    }

    private Team map(TeamDTO teamDTO) {
        Team team = new Team();
        team.setPlayers(mapPlayerDTO(teamDTO.getPlayerDTO()));
        team.setTeamName(teamDTO.getTeamName());
        return team;
    }

    private List<Player> mapPlayerDTO(List<PlayerDTO> playersDTO) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playersDTO.size(); i++) {
            Player player = new Player();
            player.setId(playersDTO.get(i).getId());
            player.setPlayerName(playersDTO.get(i).getName());
            players.add(player);
        }
        return players;
    }

    private List<PlayerDTO> mapPlayer(List<Player> players) {
        List<PlayerDTO> playersDTO = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            playersDTO.add(new PlayerDTO(players.get(i).getId(), players.get(i).getPlayerName()));
        }
        return playersDTO;
    }

    private List<GameDTO> mapGame(List<Game> games) {
        List<GameDTO> gamesDTO = new ArrayList<>();
        for (int i = 0; i < games.size(); i++) {
            gamesDTO.add(new GameDTO(games.get(i).getId(), games.get(i).getGameName(), map(games.get(i).getFirstTeam()),
                    map(games.get(i).getSecondTeam())));
        }
        return gamesDTO;
    }

}
