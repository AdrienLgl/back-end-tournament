package com.campusacademy.b2dev.backendtournamentmanagement.api.model;

import java.util.List;

public class TeamDTO {

    private Long id;
    private String name;
    private List<PlayerDTO> players;

    public TeamDTO(Long id, String name, List<PlayerDTO> players) {
        this.id = id;
        this.name = name;
        this.players = players;
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

    public List<PlayerDTO> getPlayerDTO() {
        return players;
    }

    public void setPlayerDTO(List<PlayerDTO> players) {
        this.players = players;
    }

}
