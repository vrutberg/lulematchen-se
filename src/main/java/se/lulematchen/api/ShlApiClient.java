package se.lulematchen.api;

import se.lulematchen.ApplicationProperties;
import se.lulematchen.api.dao.*;

public class ShlApiClient {
    private static ShlApiClient instance = null;

    private final String API_ROOT_URL = "https://openapi.shl.se";
    private String CLIENT_ID;
    private String CLIENT_SECRET;

    private ShlApi api;
    private String currentAccessToken = null;

    private ShlApiClient() {
        ApplicationProperties properties = ApplicationProperties.getInstance();
        properties.loadProperties();

        CLIENT_ID = properties.getProperty("clientId");
        CLIENT_SECRET = properties.getProperty("clientSecret");

        api = new ShlApiImpl(API_ROOT_URL);
    }

    public static ShlApiClient getInstance() {
        if (instance == null) {
            instance = new ShlApiClient();
        }

        return instance;
    }

    private void renewAccessToken() {
        AuthenticationResponse authenticationResponse = api.authenticate(CLIENT_ID, CLIENT_SECRET);
        this.currentAccessToken = authenticationResponse.getAccessToken();
    }

    public GameList getGames(Season season, TeamId... teamIds) {
        if (currentAccessToken == null) {
            renewAccessToken();
        }

        GameList games = null;

        try {
            games = api.getGames(currentAccessToken, season, teamIds);
        } catch (ExpiredAccessTokenException e) {
            System.out.println("Token expired, attempting to renew it...");
            this.renewAccessToken();

            try {
                games = api.getGames(currentAccessToken, season, teamIds);
                System.out.println("Successfully renewed access token!");
            } catch (ExpiredAccessTokenException e1) {
                e1.printStackTrace();
            }
        }

        // todo: can be null at this point, return something else or throw an exception?
        return games;
    }

    public GameInfo getGame(Season season, GameId gameId) {
        if (currentAccessToken == null) {
            renewAccessToken();
        }

        GameInfo game = null;

        try {
            game = api.getGame(currentAccessToken, season, gameId);
        } catch (ExpiredAccessTokenException e) {
            System.out.println("Token expired, attempting to renew it...");
            this.renewAccessToken();

            try {
                game = api.getGame(currentAccessToken, season, gameId);
                System.out.println("Successfully renewed access token!");
            } catch (ExpiredAccessTokenException e1) {
                e1.printStackTrace();
            }
        }

        // todo: can be null at this point, return something else or throw an exception?
        return game;
    }
}
