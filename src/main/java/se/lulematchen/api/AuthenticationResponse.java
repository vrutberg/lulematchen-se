package se.lulematchen.api;

public class AuthenticationResponse {
    private String tokenType;
    private String accessToken;
    private int expiresIn;

    public AuthenticationResponse() {}

    public AuthenticationResponse(String tokenType, String accessToken, int expiresIn) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "tokenType='" + tokenType + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
