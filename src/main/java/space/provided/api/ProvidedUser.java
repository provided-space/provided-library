package space.provided.api;

public final class ProvidedUser {

    private final String username;
    private final int rankId;
    private final String integrity;

    public ProvidedUser(String username, int rankId, String integrity) {
        this.username = username;
        this.rankId = rankId;
        this.integrity = integrity;
    }

    public String getUsername() {
        return username;
    }

    public int getRankId() {
        return rankId;
    }

    public String getIntegrity() {
        return integrity;
    }
}
