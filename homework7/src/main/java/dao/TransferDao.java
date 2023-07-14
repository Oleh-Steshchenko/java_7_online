package dao;
import db.TransferStorage;
import entity.FootballClub;
import entity.FootballPlayer;
import java.util.ArrayList;
public class TransferDao {
    TransferStorage transferStorage = TransferStorage.getInstance();
    public void createPlayer(FootballPlayer player) {
        transferStorage.createPlayer(player);
    }
    public void updatePlayer(int index, FootballPlayer player) {
        transferStorage.updatePlayer(index, player);
    }
    public void deletePlayer(int index) {
        transferStorage.deletePlayer(index);
    }
    public FootballPlayer findByIdPlayer(String id) {
        return transferStorage.findByIdPlayer(id);
    }
    public ArrayList<FootballPlayer> getPlayers() {
        return transferStorage.getPlayers();
    }
    public void createClub(FootballClub club) {
        transferStorage.createClub(club);
    }
    public void updateClub(int index, FootballClub club) {
        transferStorage.updateClub(index, club);
    }
    public void deleteClub(int index) {
        transferStorage.deleteClub(index);
    }
    public FootballClub findByIdClub(String id) {
        return transferStorage.findByIdClub(id);
    }
    public ArrayList<FootballClub> getClubs() {
        return transferStorage.getClubs();
    }
    public void addPlayerToClub(String playerId, String clubId) {
        transferStorage.addPlayerToClub(playerId, clubId);
    }
    public ArrayList<FootballClub> findAllClubByPlayer(String id) {
        return transferStorage.findAllClubByPlayer(id);
    }
    public ArrayList<FootballPlayer> findAllPlayerByClub(String id) {
        return transferStorage.findAllPlayerByClub(id);
    }
    public ArrayList<FootballPlayer> findAllAddedData() {
        return transferStorage.findAllAddedData();
    }
}