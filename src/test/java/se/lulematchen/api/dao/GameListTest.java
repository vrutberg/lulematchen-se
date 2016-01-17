package se.lulematchen.api.dao;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameListTest {

    @Test
    public void getLastPlayedGame_shouldReturnTheCorrectGame() {
        GameBuilder gameBuilder = new GameBuilder();

        Game gameOne = gameBuilder.aGame().withGameId(1).withStartDateTime(LocalDateTime.now().minusDays(2)).withPlayed(true).build();
        Game gameTwo = gameBuilder.aGame().withGameId(2).withStartDateTime(LocalDateTime.now().plusDays(10)).withPlayed(false).build();
        Game gameThree = gameBuilder.aGame().withGameId(3).withStartDateTime(LocalDateTime.now().minusDays(1)).withPlayed(true).build();

        GameList gameList = new GameList(Arrays.asList(gameOne, gameTwo, gameThree));

        Game lastPlayedGame = gameList.getLastPlayedGame().get();

        assertEquals(3, lastPlayedGame.getGameId());
    }

    @Test
    public void getGamesByDate_shouldReturnOneGame_whenThereIsAGameThatMatches() throws Exception {
        GameBuilder gameBuilder = new GameBuilder();

        Game game = gameBuilder.aGame().withStartDateTime(LocalDateTime.now()).build();
        GameList gameList = new GameList(Arrays.asList(game));
        List<Game> listOfGames = gameList.getGamesByDate(LocalDate.now());

        assertEquals(1, listOfGames.size());
    }

    @Test
    public void getGamesByDate_shouldNotReturnAnyGames_whenThereAreNoGamesThatMatch() throws Exception {
        GameBuilder gameBuilder = new GameBuilder();

        Game game = gameBuilder.aGame().withStartDateTime(LocalDateTime.now().plusDays(1)).build();
        GameList gameList = new GameList(Arrays.asList(game));
        List<Game> listOfGames = gameList.getGamesByDate(LocalDate.now());

        assertEquals(0, listOfGames.size());
    }

    @Test
    public void getGamesByDate_shouldReturnOneGame_whenThereAreBothMatchingAndNonMatchingGames() throws Exception {
        GameBuilder gameBuilder = new GameBuilder();

        Game gameOne = gameBuilder.aGame().withStartDateTime(LocalDateTime.now()).build();
        Game gameTwo = gameBuilder.aGame().withStartDateTime(LocalDateTime.now().plusDays(1)).build();
        GameList gameList = new GameList(Arrays.asList(gameOne, gameTwo));
        List<Game> listOfGames = gameList.getGamesByDate(LocalDate.now());

        assertEquals(1, listOfGames.size());
    }
}
