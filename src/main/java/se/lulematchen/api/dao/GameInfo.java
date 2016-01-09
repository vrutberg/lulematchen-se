package se.lulematchen.api.dao;

import java.util.List;

public class GameInfo {
    private LiveInfo live;

    private int gameId;
    private int season;
    private String series;
    private String gameType;
    private int roundNumber;
    private String startDateTime;
    private String homeTeamCode;
    private int homeTeamResult;
    private String awayTeamCode;
    private int awayTeamResult;
    private String periodResults;
    private boolean gameCenterActive;
    private boolean played;
    private boolean overtime;
    private boolean penaltyShots;
    private String ticketUrl;
    private String gameCenterUrlDesktop;
    private String gameCenterUrlMobile;
    private List<String> tvChannels;
    private String venue;

    public GameInfo() {
    }

    public GameInfo(LiveInfo live, int gameId, int season, String series, String gameType, int roundNumber, String startDateTime, String homeTeamCode, int homeTeamResult, String awayTeamCode, int awayTeamResult, String periodResults, boolean gameCenterActive, boolean played, boolean overtime, boolean penaltyShots, String ticketUrl, String gameCenterUrlDesktop, String gameCenterUrlMobile, List<String> tvChannels, String venue) {
        this.live = live;
        this.gameId = gameId;
        this.season = season;
        this.series = series;
        this.gameType = gameType;
        this.roundNumber = roundNumber;
        this.startDateTime = startDateTime;
        this.homeTeamCode = homeTeamCode;
        this.homeTeamResult = homeTeamResult;
        this.awayTeamCode = awayTeamCode;
        this.awayTeamResult = awayTeamResult;
        this.periodResults = periodResults;
        this.gameCenterActive = gameCenterActive;
        this.played = played;
        this.overtime = overtime;
        this.penaltyShots = penaltyShots;
        this.ticketUrl = ticketUrl;
        this.gameCenterUrlDesktop = gameCenterUrlDesktop;
        this.gameCenterUrlMobile = gameCenterUrlMobile;
        this.tvChannels = tvChannels;
        this.venue = venue;
    }

    public LiveInfo getLive() {
        return live;
    }

    public void setLive(LiveInfo live) {
        this.live = live;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
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

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getHomeTeamCode() {
        return homeTeamCode;
    }

    public void setHomeTeamCode(String homeTeamCode) {
        this.homeTeamCode = homeTeamCode;
    }

    public int getHomeTeamResult() {
        return homeTeamResult;
    }

    public void setHomeTeamResult(int homeTeamResult) {
        this.homeTeamResult = homeTeamResult;
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

    public String getPeriodResults() {
        return periodResults;
    }

    public void setPeriodResults(String periodResults) {
        this.periodResults = periodResults;
    }

    public boolean isGameCenterActive() {
        return gameCenterActive;
    }

    public void setGameCenterActive(boolean gameCenterActive) {
        this.gameCenterActive = gameCenterActive;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public boolean isOvertime() {
        return overtime;
    }

    public void setOvertime(boolean overtime) {
        this.overtime = overtime;
    }

    public boolean isPenaltyShots() {
        return penaltyShots;
    }

    public void setPenaltyShots(boolean penaltyShots) {
        this.penaltyShots = penaltyShots;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
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

    public List<String> getTvChannels() {
        return tvChannels;
    }

    public void setTvChannels(List<String> tvChannels) {
        this.tvChannels = tvChannels;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
