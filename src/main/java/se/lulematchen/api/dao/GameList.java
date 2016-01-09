package se.lulematchen.api.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameList implements Serializable {
    private List<Game> games;

    public GameList() {
        games = new ArrayList<>();
    }

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public int hashCode() {
        return games.hashCode();
    }
}
