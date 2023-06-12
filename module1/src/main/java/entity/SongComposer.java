package entity;
public class SongComposer {
    private static String songId;
    private String composerId;
    public static String getSongId() {
        return songId;
    }
    public void setSongIdId(String songId) {
        this.songId = songId;
    }
    public String getComposerId() {
        return composerId;
    }
    public void setComposerId(String composerId) {
        this.composerId = composerId;
    }
    @Override
    public String toString() {
        return "ID of  the song: " + getSongId() +
                ", ID of the composer: " + getComposerId();
    }
}