package se.lulematchen;

import se.lulematchen.api.ShlApiClient;
import se.lulematchen.api.dao.GameList;
import se.lulematchen.api.dao.Season;
import se.lulematchen.api.dao.TeamId;

public class GameFetcher implements Runnable {
    public void run() {
        ShlApiClient client = ShlApiClient.getInstance();
        ApplicationDataCache data = ApplicationDataCache.getInstance();

        GameList games = client.getGames(Season.fromInt(2015), TeamId.fromString("LHF"));

        System.out.println(String.format("got %d games", games.getGames().size()));

        data.putGames(games);
    }
}
