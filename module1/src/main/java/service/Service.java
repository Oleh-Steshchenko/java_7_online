package service;
import dao.Dao;
import entity.Composer;
import entity.Song;
import entity.SongComposer;
import java.io.BufferedReader;
public class Service {
    private Dao dao = new Dao();
    public void addComposer(Composer composer) {
        if (composer != null) {
            System.out.println("Composer added!");
            dao.addComposer(composer);
        }else
            System.out.println("Eror");
    }
    public void addSong(Song song) {
        if (song!=null){
            System.out.println("Song added!");
        dao.addSong(song);
    }else
            System.out.println("Eror");
    }
    public void addSongToComposer(String songId, String composerId) {
        if (songId != null) {
            if (composerId != null)
                System.out.println("You have added a song to the composer");
            dao.addSongToComposer(songId, composerId);
        }else
            System.out.println("Eror");
    }
    public Song[] findSongByComposer(String composerId) {
        return dao.findSongByComposer(composerId);
    }
    public void updateComposer(Composer composer) {
        if (composer!=null){
            System.out.println("Composer added!");
        dao.updateComposer(composer);
        }else
            System.out.println("Eror");
        }
    public void updateSong(Song song) {
        if (song!=null){
            System.out.println("Song added!");
        dao.updateSong(song);
        }else
            System.out.println("Eror");
    }
    public void deleteComposer(String composerId) {
        if (composerId!=null){
            System.out.println("The composer has been deleted!");
        dao.deleteComposer(composerId);
    }else
            System.out.println("Eror");
    }
    public void deleteSong(String songId) {
        if (songId!=null){
            System.out.println("The song has been deleted!");
        dao.deleteSong(songId);
    }else
            System.out.println("Eror");
    }
        public Song[] findAllSongs() {
        return dao.findAllSongs();
    }
    public Composer[] findAllComposers() {
        return dao.findAllComposers();
    }
    public SongComposer[] findAllSongsComposers() {
        return dao.findAllSongsComposers();
    }
}