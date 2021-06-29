package com.campusacademy.b2dev.backendtournamentmanagement.api.model;

import java.util.List;

public class TeamDTO {

    private Long id;
    private String name;
    private List<PlayerDTO> players;
    private List<GameDTO> games;

    public TeamDTO(Long id, String name, List<PlayerDTO> players, List<GameDTO> games) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return name;
    }

    public void setTeamName(String name) {
        this.name = name;
    }

}
