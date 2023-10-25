package space.provided.api;

public final class AssetItem {

    private final int id;
    private final String name;
    private final String author;
    private final String information;
    private final String lastUpdate;
    private final String[] badges;

    public AssetItem(int id, String name, String author, String information, String lastUpdate, String[] badges) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.information = information;
        this.lastUpdate = lastUpdate;
        this.badges = badges;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getInformation() {
        return information;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String[] getBadges() {
        return badges;
    }
}
