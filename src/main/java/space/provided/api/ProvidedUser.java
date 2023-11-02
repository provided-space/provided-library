package space.provided.api;

public final class ProvidedUser {

    private final String username;
    private final int rankId;

    public ProvidedUser(String username, int rankId) {
        this.username = username;
        this.rankId = rankId;
    }

    public String getUsername() {
        return username;
    }

    public int getRankId() {
        return rankId;
    }
}
