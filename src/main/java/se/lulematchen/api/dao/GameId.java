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

    @Override
    public String toString() {
        return "GameId{" +
                "gameId='" + gameId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameId)) return false;

        GameId gameId1 = (GameId) o;

        return gameId.equals(gameId1.gameId);
    }

    @Override
    public int hashCode() {
        return gameId.hashCode();
    }
}
