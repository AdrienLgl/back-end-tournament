package com.campusacademy.b2dev.backendtournamentmanagement.api.model;

public class GameDTO {
    private Long id;
    private String name;
    private TeamDTO firstTeam;
    private TeamDTO secondTeam;

    public GameDTO(Long id, String name, TeamDTO firstTeam, TeamDTO secondTeam) {
        this.id = id;
        this.name = name;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public TeamDTO getSecondTeam() {
        return secondTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamDTO getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(TeamDTO firstTeam) {
        this.firstTeam = firstTeam;
    }

    public void setSecondTeam(TeamDTO secondTeam) {
        this.secondTeam = secondTeam;
    }
}
