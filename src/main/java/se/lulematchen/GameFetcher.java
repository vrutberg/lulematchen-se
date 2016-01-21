package se.lulematchen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lulematchen.api.ShlApiClient;
import se.lulematchen.api.dao.GameList;
import se.lulematchen.api.dao.Season;
import se.lulematchen.api.dao.TeamId;

public class GameFetcher implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(GameFetcher.class);

    public void run() {
        ShlApiClient client = ShlApiClient.getInstance();
        ApplicationDataCache data = ApplicationDataCache.getInstance();

        try {
            GameList games = client.getGames(Season.fromInt(2015), TeamId.fromString("LHF"));
            logger.info(String.format("Received %d games", games.getGames().size()));
            data.putGames(games);
        } catch (Exception e) {
            logger.error("Received error while updating list of games", e);
        }
    }
}
