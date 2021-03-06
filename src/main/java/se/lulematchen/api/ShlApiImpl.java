package se.lulematchen.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.lulematchen.api.dao.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class ShlApiImpl implements ShlApi {
    private final Logger logger = LoggerFactory.getLogger(ShlApiImpl.class);

    private String apiUrl;

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private ObjectMapper objectMapper;

    public ShlApiImpl(String apiUrl) {
        this.apiUrl = apiUrl;

        this.objectMapper = getObjectMapper();
    }

    private ObjectMapper getObjectMapper() {
        JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        ObjectMapper objectMapper = jacksonJsonProvider
                .locateMapper(Object.class, MediaType.APPLICATION_JSON_TYPE);

        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        return objectMapper;
    }

    private <T> T makeRequest(HttpUriRequest request, Class<T> clazz)
            throws ExpiredAccessTokenException, IOException
    {
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_FORBIDDEN) {
                throw new ExpiredAccessTokenException();
            }

            if (response.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
                logger.warn("Got unexpected non-200 response", response);
            }

            String responseString = EntityUtils.toString(response.getEntity());

            return objectMapper.readValue(responseString, clazz);
        } catch (IOException e) {
            logger.error("Error while making HTTP request", e);
            throw e;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public AuthenticationResponse authenticate(String clientId, String clientSecret) {
        HttpUriRequest request = RequestBuilder.post(apiUrl + "/oauth2/token")
                .addParameter("grant_type", "client_credentials")
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        AuthenticationResponse deserializedResponse;

        try {
            deserializedResponse = makeRequest(request, AuthenticationResponse.class);
        } catch (IOException e) {
            logger.error("Error while authenticating", e);
            throw new RuntimeException(e);
        } catch (ExpiredAccessTokenException e) {
            logger.error("Token has expired", e);
            throw new RuntimeException(e);
        }

        if (deserializedResponse == null) {
            throw new RuntimeException("Got no response, quitting...");
        }

        return deserializedResponse;
    }

    public GameList getGames(String accessToken, Season season, TeamId... teamIds)
        throws ExpiredAccessTokenException
    {
        logger.info(String.format("Fetching games from api: [Season %s, teamIds %s]", season, teamIds));

        String urlString = String.format("/seasons/%s/games", season.valueOf());

        RequestBuilder requestBuilder = RequestBuilder.get(apiUrl + urlString)
                .addHeader("Authorization", String.format("Bearer %s", accessToken));

        if (teamIds != null) {
            for (TeamId teamId : teamIds) {
                requestBuilder.addParameter("teamIds", teamId.valueOf());
            }
        }

        HttpUriRequest request = requestBuilder.build();
        GameList deserializedResponse;

        try {
            deserializedResponse = makeRequest(request, GameList.class);
        } catch (IOException e) {
            logger.error("Error while getting games", e);
            throw new RuntimeException(e);
        }

        return deserializedResponse;
    }

    @Override
    public GameInfo getGame(String accessToken, Season season, GameId gameId)
        throws ExpiredAccessTokenException
    {
        logger.info(String.format("Fetching game details from api: [Season %s, GameId %s]", season.valueOf(), gameId.valueOf()));

        String urlString = String.format("/seasons/%s/games/%s", season.valueOf(), gameId.valueOf());

        HttpUriRequest request = RequestBuilder.get(apiUrl + urlString)
                .addHeader("Authorization", String.format("Bearer %s", accessToken)).build();

        GameInfo deserializedResponse;

        try {
            deserializedResponse = makeRequest(request, GameInfo.class);
        } catch (IOException e) {
            logger.error("Error while getting single game", e);
            throw new RuntimeException(e);
        }

        return deserializedResponse;
    }
}
