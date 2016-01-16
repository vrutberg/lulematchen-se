package se.lulematchen.api.dao;

public class TeamId {
    private final String teamId;

    private TeamId(String teamId) {
        this.teamId = teamId;
    }

    public static TeamId fromString(String season) {
        return new TeamId(season);
    }

    public String valueOf() {
        return teamId;
    }

    @Override
    public String toString() {
        return "TeamId{" +
                "teamId='" + teamId + '\'' +
                '}';
    }
}
