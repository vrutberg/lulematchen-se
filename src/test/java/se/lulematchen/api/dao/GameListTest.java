package se.lulematchen.api.dao;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GameListTest {
    @Test
    public void aTest() {
        GameBuilder gameBuilder = new GameBuilder();

        Game gameOne = gameBuilder.aGame().withGameId(1).withStartDateTime(LocalDateTime.now().minusDays(2)).withPlayed(true).build();
        Game gameTwo = gameBuilder.aGame().withGameId(2).withStartDateTime(LocalDateTime.now().plusDays(10)).withPlayed(false).build();
        Game gameThree = gameBuilder.aGame().withGameId(3).withStartDateTime(LocalDateTime.now().minusDays(1)).withPlayed(true).build();

        GameList gameList = new GameList(Arrays.asList(gameOne, gameTwo, gameThree));

        Game lastPlayedGame = gameList.getLastPlayedGame().get();

        assertEquals(3, lastPlayedGame.getGameId());
    }
}
