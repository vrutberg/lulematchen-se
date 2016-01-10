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

    private CloseableHttpResponse makeRequest(HttpUriRequest request)
            throws ExpiredAccessTokenException, IOException
    {
        try {
            CloseableHttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_FORBIDDEN) {
                throw new ExpiredAccessTokenException();
            }

            if (response.getStatusLine().getStatusCode() != HttpServletResponse.SC_OK) {
                logger.warn("Got unexpected non-200 response", response);
            }

            return response;
        } catch (IOException e) {
            logger.error("Error while making HTTP request", e);
            throw e;
        }
    }

    public AuthenticationResponse authenticate(String clientId, String clientSecret) {
        HttpUriRequest request = RequestBuilder.post(apiUrl + "/oauth2/token")
                .addParameter("grant_type", "client_credentials")
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        AuthenticationResponse deserializedResponse = null;

        try {
            CloseableHttpResponse response = this.makeRequest(request);
            String responseString = EntityUtils.toString(response.getEntity());

            deserializedResponse = objectMapper.readValue(responseString, AuthenticationResponse.class);
        } catch (IOException e) {
            logger.error("Error while authenticating", e);
        } catch (ExpiredAccessTokenException e) {
            logger.error("Token has expired", e);
        }

        if (deserializedResponse == null) {
            throw new RuntimeException("Got no response, quitting...");
        }

        return deserializedResponse;
    }

    public GameList getGames(String accessToken, Season season, TeamId... teamIds)
        throws ExpiredAccessTokenException
    {
        String urlString = String.format("/seasons/%s/games", season.valueOf());

        RequestBuilder requestBuilder = RequestBuilder.get(apiUrl + urlString)
                .addHeader("Authorization", String.format("Bearer %s", accessToken));

        if (teamIds != null) {
            for (TeamId teamId : teamIds) {
                requestBuilder.addParameter("teamIds[]", teamId.valueOf());
            }
        }

        HttpUriRequest request = requestBuilder.build();
        List<Game> deserializedResponse = null;

        try {
            CloseableHttpResponse response = makeRequest(request);
            String responseString = EntityUtils.toString(response.getEntity());

            deserializedResponse = objectMapper.readValue(responseString, new TypeReference<List<Game>>() {});
        } catch (IOException e) {
            logger.error("Error while getting games", e);
        }

        if (deserializedResponse == null) {
            throw new RuntimeException("Got no response, quitting...");
        }

        return new GameList(deserializedResponse);
    }

    @Override
    public GameInfo getGame(String accessToken, Season season, GameId gameId)
        throws ExpiredAccessTokenException
    {
        String urlString = String.format("/seasons/%s/games/%s", season.valueOf(), gameId.valueOf());

        RequestBuilder requestBuilder = RequestBuilder.get(apiUrl + urlString)
                .addHeader("Authorization", String.format("Bearer %s", accessToken));

        HttpUriRequest httpUriRequest = requestBuilder.build();

        GameInfo deserializedResponse = null;

        try {
            CloseableHttpResponse response = httpClient.execute(httpUriRequest);
            String responseString = EntityUtils.toString(response.getEntity());

            deserializedResponse = objectMapper.readValue(responseString, GameInfo.class);
        } catch (IOException e) {
            logger.error("Error while getting single game", e);
        }

        if (deserializedResponse == null) {
            throw new RuntimeException("Got no response, quitting...");
        }

        return deserializedResponse;
    }
}
