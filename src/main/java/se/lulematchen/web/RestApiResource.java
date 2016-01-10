package se.lulematchen.web;

import se.lulematchen.ApplicationDataCache;
import se.lulematchen.api.ShlApiClient;
import se.lulematchen.api.dao.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RestApiResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games")
    public Response getGames() {
        GameList gameList = ApplicationDataCache.getInstance().getGames();

        if (gameList == null) {
            return Response.noContent().build();
        }

        return Response.ok(gameList.getGames()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games/{gameId}/details")
    public Response getGameInfo(@PathParam("gameId") String gameId) {
        GameInfo game = ShlApiClient.getInstance().getGame(Season.fromString("2015"), GameId.fromString(gameId));

        if (game == null) {
            return Response.noContent().build();
        }

        return Response.ok(game).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games/lastPlayed")
    public Response getLastPlayedGame(@PathParam("gameId") String gameId) {
        GameList games = ApplicationDataCache.getInstance().getGames();
        Game lastPlayedGame = games.getLastPlayedGame().get();

        if (lastPlayedGame == null) {
            return Response.noContent().build();
        }

        return Response.ok(lastPlayedGame).build();
    }
}
