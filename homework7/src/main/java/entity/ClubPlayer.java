package entity;
public class ClubPlayer {
    private FootballPlayer player;
    private FootballClub club;
    public FootballPlayer getPlayer() {
        return player;
    }
    public void setPlayer(FootballPlayer player) {
        this.player = player;
    }
    public FootballClub getClub() {
        return club;
    }
    public void setClub(FootballClub club) {
        this.club = club;
    }
    public ClubPlayer(FootballPlayer player, FootballClub club) {
        this.player = player;
        this.club = club;
    }
}