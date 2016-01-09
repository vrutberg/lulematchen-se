package se.lulematchen.api.dao;

public class TeamId {
    private String teamId;

    private TeamId(String teamId) {
        this.teamId = teamId;
    }

    public static TeamId fromString(String season) {
        return new TeamId(season);
    }

    public String valueOf() {
        return teamId;
    }
}
