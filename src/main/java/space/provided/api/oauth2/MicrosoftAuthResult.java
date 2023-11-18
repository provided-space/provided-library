package space.provided.api.oauth2;

public class MicrosoftAuthResult {

    private final String id;
    private final String name;
    private final String accessToken;
    private final String refreshToken;

    public MicrosoftAuthResult(String id, String name, String accessToken, String refreshToken) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
