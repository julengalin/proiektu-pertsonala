package dambi.nbarestapi.models;

import java.util.List;

public class Conference {
    
    private String conferenceName;
    private int teamCount; // Número de equipos en la conferencia
    private List<String> teams; // Lista de equipos en orden alfabético

    // Constructor, getters y setters

    @Override
    public String toString() {
        return "Conference [conferenceName=" + conferenceName + ", teamCount=" + teamCount + ", teams=" + teams + "]";
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }
}
