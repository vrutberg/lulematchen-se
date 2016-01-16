package se.lulematchen.api.dao;

public class LiveInfo {
    private String gameTime;
    private int timePeriod;
    private int gameId;
    private int period;
    private int round;
    private String homeTeamCode;
    private int homeScore;
    private String awayTeamCode;
    private int awayScore;
    private String venue;
    private int attendance;
    private String statusString;

    public LiveInfo() {
    }

    public LiveInfo(String gameTime, int timePeriod, int gameId, int period, int round, String homeTeamCode, int homeScore, String awayTeamCode, int awayScore, String venue, int attendance, String statusString) {
        this.gameTime = gameTime;
        this.timePeriod = timePeriod;
        this.gameId = gameId;
        this.period = period;
        this.round = round;
        this.homeTeamCode = homeTeamCode;
        this.homeScore = homeScore;
        this.awayTeamCode = awayTeamCode;
        this.awayScore = awayScore;
        this.venue = venue;
        this.attendance = attendance;
        this.statusString = statusString;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public int getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getHomeTeamCode() {
        return homeTeamCode;
    }

    public void setHomeTeamCode(String homeTeamCode) {
        this.homeTeamCode = homeTeamCode;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayTeamCode() {
        return awayTeamCode;
    }

    public void setAwayTeamCode(String awayTeamCode) {
        this.awayTeamCode = awayTeamCode;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    @Override
    public String toString() {
        return "LiveInfo{" +
                "gameTime='" + gameTime + '\'' +
                ", timePeriod=" + timePeriod +
                ", gameId=" + gameId +
                ", period=" + period +
                ", round=" + round +
                ", homeTeamCode='" + homeTeamCode + '\'' +
                ", homeScore=" + homeScore +
                ", awayTeamCode='" + awayTeamCode + '\'' +
                ", awayScore=" + awayScore +
                ", venue='" + venue + '\'' +
                ", attendance=" + attendance +
                ", statusString='" + statusString + '\'' +
                '}';
    }
}
