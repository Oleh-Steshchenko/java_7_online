package db;
import entity.Composer;
import entity.Song;
import entity.SongComposer;
import java.util.UUID;
public class Storage {
    private static Storage instance;
    private Composer[] composers = new Composer[10];
    private Song[] songs = new Song[10];
    private SongComposer[] songComposers = new SongComposer[10];
    private Storage() {
    }
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    public void addComposer(Composer composer) {
        composer.setId(generateIdForComposer());
        for (int i = 0; i < composers.length; i++) {
            if (composers[i] == null) {
                composers[i] = composer;
                break;
            }
        }
    }
    public void addSong(Song song) {
        song.setId(generateIdForSong());
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] == null) {
                songs[i] = song;
                break;
            }
        }
    }
    public void addSongToComposer(String songId, String composerId) {
        for (int i = 0; i < songComposers.length; i++) {
            if (songComposers[i] == null) {
                SongComposer songComposer = new SongComposer();
                songComposer.setComposerId(composerId);
                songComposer.setSongIdId(songId);
                songComposers[i] = songComposer;
                break;
            }
        }
    }
    public Song[] findSongByComposer(String composerId) {
        String[] songIds = new String[10];
        for (int i = 0; i < songComposers.length; i++) {
            SongComposer songComposer = songComposers[i];
            if (songComposer != null && songComposer.getComposerId().equals(composerId)) {
                for (int i1 = 0; i1 < songIds.length; i1++) {
                    if (songIds[i1] == null) {
                        songIds[i1] = SongComposer.getSongId();
                        break;
                    }
                }
            }
        }
        Song[] songs = new Song[10];
        for (int i = 0; i < this.songs.length; i++) {
            for (int i1 = 0; i1 < songIds.length; i1++) {
                if (this.songs[i] != null && this.songs[i].getId().equals(songIds[i1])) {
                    for (int i2 = 0; i2 < songs.length; i2++) {
                        if (songs[i2] == null) {
                            songs[i2] = this.songs[i];
                            break;
                        }
                    }
                }
            }
        }
        return songs;
    }
    public void updateComposer(Composer composer) {
        for (int i = 0; i < composers.length; i++) {
            try {
                if (composers[i].getId().equals(composer.getId())) {
                    composers[i] = composer;
                }
            } catch (Exception e) {
                i++;
            }
        }
    }
    public void updateSong(Song song) {
        for (int i = 0; i < songs.length; i++) {
            try {
                if (songs[i].getId().equals(song.getId())) {
                    songs[i] = song;
                }
            } catch (Exception e) {
                i++;
            }
        }
    }
    public void deleteComposer(String composerId) {
        for (int i = 0; i < composers.length; i++) {
            try {
                if (composers[i].getId().equals(composerId)) {
                    composers[i] = null;
                    break;
                }
            } catch (Exception exception) {
                i++;
            }
        }
        for (int i = 0; i < songComposers.length; i++) {
            try {
                if (songComposers[i].getComposerId().equals(composerId)) {
                    songComposers[i] = null;
                    break;
                }
            } catch (Exception exception) {
                i++;
            }
        }
    }
    public void deleteSong(String songId) {
        for (int i = 0; i < songs.length; i++) {
            try {
                if (songs[i].getId().equals(songId)) {
                    songs[i] = null;
                }
            } catch (Exception exception) {
                i++;
            }
        }
    }
    public Song[] findAllSongs() {
        return this.songs;
    }
    public Composer[] findAllComposers() {
        return this.composers;
    }
    public SongComposer[] findAllSongsComposers() {
        return this.songComposers;
    }
    private String generateIdForComposer() {
        String id = UUID.randomUUID().toString();
        for (Composer composer : composers) {
            if (composer != null && composer.getId().equals(id)) {
                return generateIdForComposer();
            }
        }
        return id;
    }
    private String generateIdForSong() {
        String id = UUID.randomUUID().toString();
        for (Song song : songs) {
            if (song != null && song.getId().equals(id)) {
                return generateIdForSong();
            }
        }
        return id;
    }
}