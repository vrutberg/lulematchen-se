package se.lulematchen.web;

import se.lulematchen.ApplicationDataCache;
import se.lulematchen.api.ShlApiClient;
import se.lulematchen.api.dao.GameId;
import se.lulematchen.api.dao.GameInfo;
import se.lulematchen.api.dao.GameList;
import se.lulematchen.api.dao.Season;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RestApiResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games")
    public GameList getGames() {
        return ApplicationDataCache.getInstance().getGames();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/game/{gameId}")
    public GameInfo getGameInfo(@PathParam("gameId") String gameId) {
        return ShlApiClient.getInstance().getGame(Season.fromString("2015"),GameId.fromString(gameId));
    }
}
