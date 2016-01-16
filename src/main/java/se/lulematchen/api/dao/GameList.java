package se.lulematchen.api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameList {
    private final List<Game> games;

    public GameList() {
        games = new ArrayList<>();
    }

    public GameList(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public Optional<Game> getLastPlayedGame() {
        return games.stream()
                .filter(Game::isPlayed)
                .sorted((o1, o2) -> o2.getStartDateTime().compareTo(o1.getStartDateTime()))
                .findFirst();
    }

    public Optional<Game> getFirstUnplayedGame() {
        return games.stream()
                .filter(game -> !game.isPlayed())
                .sorted((o1, o2) -> o1.getStartDateTime().compareTo(o2.getStartDateTime()))
                .findFirst();
    }

    @Override
    public String toString() {
        return "GameList{" +
                "games=" + games +
                '}';
    }
}
