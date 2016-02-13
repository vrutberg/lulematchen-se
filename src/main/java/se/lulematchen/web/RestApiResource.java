package se.lulematchen.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import se.lulematchen.ApplicationDataCache;
import se.lulematchen.api.ShlApiClient;
import se.lulematchen.api.dao.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Path("/")
public class RestApiResource {

    private String getMockJson() throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream stream = contextClassLoader.getResourceAsStream("gameinfo.json");

        String content = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
        Closeables.closeQuietly(stream);

        return content;
    }

    private GameInfo createMockGameInfo() throws IOException {
        JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        ObjectMapper objectMapper = jacksonJsonProvider
                .locateMapper(Object.class, MediaType.APPLICATION_JSON_TYPE);

        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        String json = getMockJson();

        return objectMapper.readValue(json, GameInfo.class);
    }

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
    public Response getGameInfo(@PathParam("gameId") String gameId) throws IOException {
        if (gameId.equals("1337")) {
            return Response.ok(createMockGameInfo()).build();
        }

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games/firstUnplayed")
    public Response getFirstUnplayedGame(@PathParam("gameId") String gameId) {
        GameList games = ApplicationDataCache.getInstance().getGames();
        Game firstUnplayedGame = games.getFirstUnplayedGame().get();

        if (firstUnplayedGame == null) {
            return Response.noContent().build();
        }

        return Response.ok(firstUnplayedGame).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/games/todaysGames")
    public Response getTodaysGames() {
        GameList games = ApplicationDataCache.getInstance().getGames();
        List<Game> todaysGames = games.getGamesByDate(LocalDate.now());

        if (todaysGames == null) {
            return Response.noContent().build();
        }

        return Response.ok(todaysGames).build();
    }
}
