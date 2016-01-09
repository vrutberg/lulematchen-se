package se.lulematchen.api;

import se.lulematchen.api.dao.GameList;
import se.lulematchen.api.dao.Season;
import se.lulematchen.api.dao.TeamId;

public interface ShlApi {
    AuthenticationResponse authenticate(String clientId, String clientSecret);
    GameList getGames(String accessToken, Season season, TeamId... teamIds);
}
