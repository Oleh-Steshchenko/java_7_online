package entity;
public abstract class BaseEntityFootballPlayer {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "BaseEntityFootballPlayer:" +
                "ID:" + id;
    }
}