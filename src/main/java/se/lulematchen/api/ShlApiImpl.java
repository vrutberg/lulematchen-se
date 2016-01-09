package se.lulematchen.api;

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
import se.lulematchen.api.dao.Game;
import se.lulematchen.api.dao.GameList;
import se.lulematchen.api.dao.Season;
import se.lulematchen.api.dao.TeamId;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class ShlApiImpl implements ShlApi {
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

    public AuthenticationResponse authenticate(String clientId, String clientSecret) {
        HttpUriRequest request = RequestBuilder.post(apiUrl + "/oauth2/token")
                .addParameter("grant_type", "client_credentials")
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        AuthenticationResponse deserializedResponse = null;

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            String responseString = EntityUtils.toString(response.getEntity());

            deserializedResponse = objectMapper.readValue(responseString, AuthenticationResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (deserializedResponse == null) {
            throw new RuntimeException("Got no response, quitting...");
        }

        return deserializedResponse;
    }

    public GameList getGames(String accessToken, Season season, TeamId... teamIds) {
        RequestBuilder requestBuilder = RequestBuilder.get(apiUrl + String.format("/seasons/%s/games", season.valueOf()))
                .addHeader("Authorization", String.format("Bearer %s", accessToken));

        if (teamIds != null) {
            for (TeamId teamId : teamIds) {
                requestBuilder.addParameter("teamIds[]", teamId.valueOf());
            }
        }

        HttpUriRequest httpUriRequest = requestBuilder.build();

        List<Game> deserializedResponse = null;

        try {
            CloseableHttpResponse response = httpClient.execute(httpUriRequest);
            String responseString = EntityUtils.toString(response.getEntity());

            deserializedResponse = objectMapper.readValue(responseString, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (deserializedResponse == null) {
            throw new RuntimeException("Got no response, quitting...");
        }

        return new GameList(deserializedResponse);
    }
}
