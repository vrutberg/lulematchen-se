package se.lulematchen.api.dao;

public class GameId {
    private final String gameId;

    private GameId(String gameId) {
        this.gameId = gameId;
    }

    public static GameId fromString(String gameId) {
        return new GameId(gameId);
    }

    public String valueOf() {
        return this.gameId;
    }
}
