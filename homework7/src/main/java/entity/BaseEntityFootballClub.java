package entity;
public abstract class BaseEntityFootballClub {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "BaseEntityFootballClub" +
                "ID:" + id ;
    }
}