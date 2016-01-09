package se.lulematchen.api.dao;

public class Game {
    private String awayTeamCode;
    private int awayTeamResult;
    private boolean gameCenterActive;
    private String gameCenterUrlDesktop;
    private String gameCenterUrlMobile;
    private int gameId;
    private String gameType;
    private String homeTeamCode;
    private String homeTeamResult;
    private boolean overTime;
    private boolean penaltyShots;
    private boolean played;
    private int roundNumber;
    private int season;
    private String series;
    private String startDateTime;
    private String ticketUrl;
    private String venue;

    public Game() {
    }

    public Game(String awayTeamCode, int awayTeamResult, boolean gameCenterActive, String gameCenterUrlDesktop, String gameCenterUrlMobile, int gameId, String gameType, String homeTeamCode, String homeTeamResult, boolean overTime, boolean penaltyShots, boolean played, int roundNumber, int season, String series, String startDateTime, String ticketUrl, String venue) {
        this.awayTeamCode = awayTeamCode;
        this.awayTeamResult = awayTeamResult;
        this.gameCenterActive = gameCenterActive;
        this.gameCenterUrlDesktop = gameCenterUrlDesktop;
        this.gameCenterUrlMobile = gameCenterUrlMobile;
        this.gameId = gameId;
        this.gameType = gameType;
        this.homeTeamCode = homeTeamCode;
        this.homeTeamResult = homeTeamResult;
        this.overTime = overTime;
        this.penaltyShots = penaltyShots;
        this.played = played;
        this.roundNumber = roundNumber;
        this.season = season;
        this.series = series;
        this.startDateTime = startDateTime;
        this.ticketUrl = ticketUrl;
        this.venue = venue;
    }

    public String getAwayTeamCode() {
        return awayTeamCode;
    }

    public void setAwayTeamCode(String awayTeamCode) {
        this.awayTeamCode = awayTeamCode;
    }

    public int getAwayTeamResult() {
        return awayTeamResult;
    }

    public void setAwayTeamResult(int awayTeamResult) {
        this.awayTeamResult = awayTeamResult;
    }

    public boolean isGameCenterActive() {
        return gameCenterActive;
    }

    public void setGameCenterActive(boolean gameCenterActive) {
        this.gameCenterActive = gameCenterActive;
    }

    public String getGameCenterUrlDesktop() {
        return gameCenterUrlDesktop;
    }

    public void setGameCenterUrlDesktop(String gameCenterUrlDesktop) {
        this.gameCenterUrlDesktop = gameCenterUrlDesktop;
    }

    public String getGameCenterUrlMobile() {
        return gameCenterUrlMobile;
    }

    public void setGameCenterUrlMobile(String gameCenterUrlMobile) {
        this.gameCenterUrlMobile = gameCenterUrlMobile;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getHomeTeamCode() {
        return homeTeamCode;
    }

    public void setHomeTeamCode(String homeTeamCode) {
        this.homeTeamCode = homeTeamCode;
    }

    public String getHomeTeamResult() {
        return homeTeamResult;
    }

    public void setHomeTeamResult(String homeTeamResult) {
        this.homeTeamResult = homeTeamResult;
    }

    public boolean isOverTime() {
        return overTime;
    }

    public void setOverTime(boolean overTime) {
        this.overTime = overTime;
    }

    public boolean isPenaltyShots() {
        return penaltyShots;
    }

    public void setPenaltyShots(boolean penaltyShots) {
        this.penaltyShots = penaltyShots;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
