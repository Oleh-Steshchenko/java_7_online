package entity;
public class FootballClub extends BaseEntityFootballClub {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Club:" +
                "Name:" + name + ", ID:"+getId();
    }
}