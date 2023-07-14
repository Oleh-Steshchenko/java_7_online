package service;
import dao.TransferDao;
import entity.FootballClub;
import entity.FootballPlayer;
import java.util.ArrayList;
public class TransferService {
    TransferDao transferDao = new TransferDao();
    public void createPlayer(FootballPlayer player) {
        transferDao.createPlayer(player);
    }
    public void updatePlayer(int index, FootballPlayer player) {
        transferDao.updatePlayer(index, player);
    }
    public void deletePlayer(int index) {
        transferDao.deletePlayer(index);
    }
    public FootballPlayer findByIdPlayer(String id) {
        return transferDao.findByIdPlayer(id);
    }
    public ArrayList<FootballPlayer> getPlayers() {
        return transferDao.getPlayers();
    }
    public void createClub(FootballClub club) {
        transferDao.createClub(club);
    }
    public void updateClub(int index, FootballClub club) {
        transferDao.updateClub(index, club);
    }
    public void deleteClub(int index) {
        transferDao.deleteClub(index);
    }
    public FootballClub findByIdClub(String id) {
        return transferDao.findByIdClub(id);
    }
    public ArrayList<FootballClub> getClubs() {
        return transferDao.getClubs();
    }
    public void addPlayerToClub(String playerId, String clubId) {
        transferDao.addPlayerToClub(playerId, clubId);
    }
    public ArrayList<FootballClub> findAllClubByPlayer(String id) {
        return transferDao.findAllClubByPlayer(id);
    }
    public ArrayList<FootballPlayer> findAllPlayerByClub(String id) {
        return transferDao.findAllPlayerByClub(id);
    }
    public ArrayList<FootballPlayer> findAllAddedData() {
        return transferDao.findAllAddedData();
    }
}