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

    /**
     * @return Encoded and signed payload which can be used to validate the integrity of the provided data. The Payload
     * can be used to serialize a ProvidedUser instance later on to allow for an offline login. See the full example
     * in the README for checking for expiration.
     */
    public String getIntegrity() {
        return integrity;
    }
}
