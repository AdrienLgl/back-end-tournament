package com.campusacademy.b2dev.backendtournamentmanagement.api.model;

public class PlayerDTO {

    private Long id;

    private String name;

    private int nbTeams;

    public PlayerDTO(Long id, String name, int nbTeams) {
        this.id = id;
        this.name = name;
        this.nbTeams = nbTeams;
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

    public int getnbTeams() {
        return nbTeams;
    }

    public void setnbTeams(int nbTeams) {
        this.nbTeams = nbTeams;
    }

}
