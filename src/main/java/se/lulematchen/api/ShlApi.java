package se.lulematchen.api;

import se.lulematchen.api.dao.*;

public interface ShlApi {
    AuthenticationResponse authenticate(String clientId, String clientSecret);
    GameList getGames(String accessToken, Season season, TeamId... teamIds);
    GameInfo getGame(String accessToken, Season season, GameId gameId);
}
