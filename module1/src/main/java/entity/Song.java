package entity;
public class Song extends BaseEntity {
    private String songName;
    private String songGenre;
    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getSongGenre() {
        return songGenre;
    }
    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }
    @Override
    public String toString() {
        return "Song" +
                "ID: " + getId() +
                ", Name: " + getSongName() +
                ", Genre: " + songGenre;
    }
}