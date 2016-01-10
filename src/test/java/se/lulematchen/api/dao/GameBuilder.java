package se.lulematchen.api.dao;

import java.time.LocalDateTime;

public class GameBuilder {
    private Game game = null;

    public GameBuilder aGame() {
        game = new Game("SAIK",
                0,
                false,
                null,
                null,
                40003,
                "regular",
                "VLH",
                0,
                false,
                false,
                false,
                52,
                2015,
                "SHL",
                LocalDateTime.now(),
                "http:\\/\\/biljett.vaxjolakers.se\\/Home\\/tickets\\/148\\/False?style=shl",
                "Vida Arena"
        );

        return this;
    }

    public GameBuilder withStartDateTime(LocalDateTime startDateTime) {
        game.setStartDateTime(startDateTime);
        return this;
    }

    public GameBuilder withGameId(int gameId) {
        game.setGameId(gameId);
        return this;
    }

    public GameBuilder withPlayed(boolean played) {
        game.setPlayed(played);
        return this;
    }

    public Game build() {
        return game;
    }
}
