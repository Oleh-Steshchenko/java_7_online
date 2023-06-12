package dao;
import db.Storage;
import entity.Composer;
import entity.Song;
import entity.SongComposer;
public class Dao {
    private Storage storage = Storage.getInstance();
    public void addComposer(Composer composer) {
        storage.addComposer(composer);
    }
    public void addSong(Song song) {
        storage.addSong(song);
    }
    public void addSongToComposer(String songId, String composerId) {
        storage.addSongToComposer(songId, composerId);
    }
    public Song[] findSongByComposer(String composerId) {
        return storage.findSongByComposer(composerId);
    }
    public void updateComposer(Composer composer) {
        storage.updateComposer(composer);
    }
    public void updateSong(Song song) {
        storage.updateSong(song);
    }
    public void deleteComposer(String composerId) {
        storage.deleteComposer(composerId);
    }
    public void deleteSong(String songId) {
        storage.deleteSong(songId);
    }
    public Song[] findAllSongs() {
        return storage.findAllSongs();
    }
    public Composer[] findAllComposers() {
        return storage.findAllComposers();
    }
    public SongComposer[] findAllSongsComposers() {
        return storage.findAllSongsComposers();
    }
}