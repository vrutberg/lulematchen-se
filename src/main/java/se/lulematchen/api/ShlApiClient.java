package se.lulematchen.api;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lulematchen.ApplicationProperties;
import se.lulematchen.api.dao.*;

import java.util.concurrent.TimeUnit;

public class ShlApiClient {
    private final Logger logger = LoggerFactory.getLogger(ShlApiClient.class);

    private static ShlApiClient instance = null;

    private final String API_ROOT_URL = "https://openapi.shl.se";
    private String CLIENT_ID;
    private String CLIENT_SECRET;

    private ShlApi api;
    private String currentAccessToken = null;

    private LoadingCache<GameRequest, GameInfo> gameInfoCache;

    private ShlApiClient() {
        ApplicationProperties properties = ApplicationProperties.getInstance();
        properties.loadProperties();

        CLIENT_ID = properties.getProperty("clientId");
        CLIENT_SECRET = properties.getProperty("clientSecret");

        api = new ShlApiImpl(API_ROOT_URL);

        gameInfoCache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build(new CacheLoader<GameRequest, GameInfo>() {
                    @Override
                    public GameInfo load(GameRequest gameRequest) throws Exception {
                        return getGameFromApi(gameRequest);
                    }
                });
    }

    public static ShlApiClient getInstance() {
        if (instance == null) {
            instance = new ShlApiClient();
        }

        return instance;
    }

    private void renewAccessToken() {
        logger.info("Attempting to renew access token...");

        AuthenticationResponse authenticationResponse = api.authenticate(CLIENT_ID, CLIENT_SECRET);
        this.currentAccessToken = authenticationResponse.getAccessToken();

        logger.info(String.format("Setting new access token: %s", this.currentAccessToken));
    }

    public GameList getGames(Season season, TeamId... teamIds) {
        if (currentAccessToken == null) {
            renewAccessToken();
        }

        GameList games = null;

        try {
            games = api.getGames(currentAccessToken, season, teamIds);
        } catch (ExpiredAccessTokenException e) {
            logger.info("Token expired, attempting to renew it...");
            this.renewAccessToken();

            try {
                games = api.getGames(currentAccessToken, season, teamIds);
                logger.info("Successfully renewed access token!");
            } catch (ExpiredAccessTokenException e1) {
                e1.printStackTrace();
            }
        }

        // todo: can be null at this point, return something else or throw an exception?
        return games;
    }

    public GameInfo getGame(Season season, GameId gameId) {
        return gameInfoCache.getUnchecked(new GameRequest(season, gameId));
    }

    private GameInfo getGameFromApi(GameRequest gameRequest) {
        if (currentAccessToken == null) {
            renewAccessToken();
        }

        GameInfo game = null;

        try {
            game = api.getGame(currentAccessToken, gameRequest.getSeason(), gameRequest.getGameId());
        } catch (ExpiredAccessTokenException e) {
            logger.info("Token expired, attempting to renew it...");
            this.renewAccessToken();

            try {
                game = api.getGame(currentAccessToken, gameRequest.getSeason(), gameRequest.getGameId());
                logger.info("Successfully renewed access token!");
            } catch (ExpiredAccessTokenException e1) {
                e1.printStackTrace();
            }
        }

        // todo: can be null at this point, return something else or throw an exception?
        return game;
    }

    private class GameRequest {
        private final Season season;
        private final GameId gameId;

        public GameRequest(Season season, GameId gameId) {
            this.season = season;
            this.gameId = gameId;
        }

        public Season getSeason() {
            return season;
        }

        public GameId getGameId() {
            return gameId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GameRequest)) return false;

            GameRequest that = (GameRequest) o;

            if (!season.equals(that.season)) return false;
            return gameId.equals(that.gameId);
        }

        @Override
        public int hashCode() {
            int result = season.hashCode();
            result = 31 * result + gameId.hashCode();
            return result;
        }
    }
}
