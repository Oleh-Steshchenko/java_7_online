package controller;
import entity.Composer;
import entity.Song;
import entity.SongComposer;
import service.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Controller {
    Service service = new Service();
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the music composition repository");
        System.out.println("Choose the section you are interested in:");
        String select;
        menu();
        while ((select = bufferedReader.readLine()) != null) {
            crud(bufferedReader, select);
        }
    }
    private static void menu() {
        System.out.println("****************************************************");
        System.out.println("Create composer, please enter: 1");
        System.out.println();
        System.out.println("Create song, please enter: 2");
        System.out.println();
        System.out.println("Update composer, please enter: 3");
        System.out.println();
        System.out.println("Update song, please enter 4:");
        System.out.println();
        System.out.println("Add song to composer enter 5:");
        System.out.println();
        System.out.println("Delete composer, please enter 6:");
        System.out.println();
        System.out.println("Delete song, please enter 7:");
        System.out.println();
        System.out.println("Find all composers 8:");
        System.out.println();
        System.out.println("Find all songs, please enter 9:");
        System.out.println();
        System.out.println("Find all composers and theirs songs, enter 10:");
        System.out.println();
        System.out.println("Find all songs by the composer, please enter 11:");
        System.out.println();
        System.out.println("Close application, please enter 0:");
        System.out.println("****************************************************");
    }
    private void crud(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> createComposer(bufferedReader);
            case "2" -> createSong(bufferedReader);
            case "3" -> updateComposer(bufferedReader);
            case "4" -> updateSong(bufferedReader);
            case "5" -> addSongToComposer(bufferedReader);
            case "6" -> deleteComposer(bufferedReader);
            case "7" -> deleteSong(bufferedReader);
            case "8" -> findAllComposers();
            case "9" -> findAllSongs();
            case "10" -> findAllSongsComposers();
            case "11" -> findSongByComposer(bufferedReader);
            case "0" -> System.exit(0);
            default -> System.out.println("You have an error, please enter again");
        }
        menu();
    }
    private void createComposer(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter first name of composer:");
        String firstName = bufferedReader.readLine();
        System.out.println("Enter last name of composer:");
        String lastName = bufferedReader.readLine();
        Composer composer = new Composer();
        composer.setFirstName(firstName);
        composer.setLastName(lastName);
        service.addComposer(composer);
    }
    private void createSong(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter name of a song:");
        String songName = bufferedReader.readLine();
        System.out.println("Enter genre of a song:");
        String songGenre = bufferedReader.readLine();
        Song song = new Song();
        song.setSongName(songName);
        song.setSongGenre(songGenre);
        service.addSong(song);
    }
    private void updateComposer(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter id:");
        String id = bufferedReader.readLine();
        System.out.println("Enter the first name:");
        String firstName = bufferedReader.readLine();
        System.out.println("Enter the last name:");
        String lastName = bufferedReader.readLine();
        Composer composer = new Composer();
        composer.setFirstName(firstName);
        composer.setLastName(lastName);
        composer.setId(id);
        service.updateComposer(composer);
    }
    private void updateSong(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter id:");
        String id = bufferedReader.readLine();
        System.out.println("Enter the name:");
        String name = bufferedReader.readLine();
        System.out.println("Enter the genre:");
        String genre = bufferedReader.readLine();
        Song song = new Song();
        song.setSongName(name);
        song.setSongGenre(genre);
        song.setId(id);
        service.updateSong(song);
    }
    private void addSongToComposer(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter the song id:");
        String bookId = bufferedReader.readLine();
        System.out.println("Please enter the composer id:");
        String authorId = bufferedReader.readLine();
        service.addSongToComposer(bookId, authorId);
    }
    private void findAllComposers() {
        Composer[] composers = service.findAllComposers();
        for (Composer composer : composers) {
            if (composer != null) {
                System.out.println("Composers: " + composer);
            }
        }
    }
    private void findAllSongs() {
        Song[] songs = service.findAllSongs();
        for (Song song : songs) {
            if (song != null) {
                System.out.println("Songs: " + song);
            }
        }
    }
    private void findAllSongsComposers() {
        SongComposer[] songComposers = service.findAllSongsComposers();
        for (SongComposer songComposer : songComposers) {
            if (songComposer != null) {
                System.out.println("Composers and songs:  " + songComposer);
            }
        }
    }
    private void findSongByComposer(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please, enter the id of the composer, which songs you want to find");
        String id = bufferedReader.readLine();
        Song[] songs = service.findSongByComposer(id);
        for (Song song : songs) {
            if (song != null) {
                System.out.println("Song: " + song);
            }
        }
    }
    private void deleteComposer(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id of the Composer, you want to delete:");
        String Id = bufferedReader.readLine();
        service.deleteComposer(Id);
        System.out.println("The composer was deleted!");
    }
    private void deleteSong(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter id of the song, you want to delete:");
        String id = bufferedReader.readLine();
        service.deleteSong(id);
        System.out.println("The song was deleted!");
    }
    }