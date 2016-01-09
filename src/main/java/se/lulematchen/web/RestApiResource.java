package se.lulematchen.web;

import se.lulematchen.ApplicationDataCache;
import se.lulematchen.api.dao.GameList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RestApiResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games")
    public GameList test() {
        return ApplicationDataCache.getInstance().getGames();
    }
}
