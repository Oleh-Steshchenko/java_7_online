package db;
import entity.FootballClub;
import entity.FootballPlayer;
import entity.ClubPlayer;
import java.util.ArrayList;
import java.util.UUID;
public class TransferStorage {
    private static TransferStorage instance;
    private TransferStorage() {
    }
    public static TransferStorage getInstance() {
        if (instance == null) {
            instance = new TransferStorage();
        }
        return instance;
    }
    private ArrayList<FootballPlayer> players = new ArrayList<FootballPlayer>();
    private ArrayList<FootballClub> clubs = new ArrayList<FootballClub>();
    private ArrayList<ClubPlayer> clubPlayers = new ArrayList<ClubPlayer>();
    private String generatePlayerId() {
        String id = UUID.randomUUID().toString();
        for (FootballPlayer player : players) {
            if (player != null && player.getId().equals(id)) {
                return generatePlayerId();
            }
        }
        return id;
    }
    public void createPlayer(FootballPlayer player) {
        if (isValidAge(player.getAge())) {
            player.setId(generatePlayerId());
            players.add(player);
        } else {
            System.out.println("Invalid age. Age must be between 18 and 50.");
        }
    }
    public void updatePlayer(int index, FootballPlayer player) {
        if (isValidAge(player.getAge())) {
            player.setId(players.get(index).getId());
            players.set(index, player);
        } else {
            System.out.println("Invalid age. Age must be between 18 and 50.");
        }
    }
    private boolean isValidAge(int age) {
        return age >= 18 && age <= 50;
    }
    public void deletePlayer(int index) {
        deleteRelationByPlayer(players.get(index).getId());
        players.remove(index);
    }
    public FootballPlayer findByIdPlayer(String id) {
        for (FootballPlayer player : players) {
            if (player.getId().equals(id)) {
                return player;
            }
        }
        return null;
    }
    public ArrayList<FootballPlayer> getPlayers() {
        return players;
    }
    private String generateClubId() {
        String id = UUID.randomUUID().toString();
        for (FootballClub club : clubs) {
            if (club != null && club.getId().equals(id)) {
                return generateClubId();
            }
        }
        return id;
    }
    public void createClub(FootballClub club) {
        club.setId(generateClubId());
        clubs.add(club);
    }
    public void updateClub(int index, FootballClub club) {
        club.setId(clubs.get(index).getId());
        clubs.set(index, club);
    }
    public void deleteClub(int index) {
        deleteRelationByClub(clubs.get(index).getId());
        clubs.remove(index);
    }
    public FootballClub findByIdClub(String id) {
        for (FootballClub club : clubs) {
            if (club.getId().equals(id)) {
                return club;
            }
        }
        return null;
    }
    public ArrayList<FootballClub> getClubs() {
        return clubs;
    }
    public void addPlayerToClub(String playerId, String clubId) {
        FootballPlayer footballPlayer = findByIdPlayer(playerId);
        FootballClub footballClub = findByIdClub(clubId);

        if (footballPlayer != null && footballClub != null) {
            clubPlayers.add(new ClubPlayer(footballPlayer, footballClub));
        } else {
            System.out.println("Invalid player ID or club ID. Player or club does not exist.");
        }
    }
    public void deleteRelationByPlayer(String id) {
        int size = clubPlayers.size();
        for (int i = 0; i < size; i++) {
            if (clubPlayers.get(i).getPlayer().getId().equals(id)) {
                clubPlayers.remove(i);
                i--;
                size = clubPlayers.size();
            }
        }
    }
    public void deleteRelationByClub(String id) {
        int size = clubPlayers.size();
        for (int i = 0; i < size; i++) {
            if (clubPlayers.get(i).getClub().getId().equals(id)) {
                clubPlayers.remove(i);
                i--;
                size = clubPlayers.size();
            }
        }
    }
    public ArrayList<FootballClub> findAllClubByPlayer(String id) {
        ArrayList<FootballClub> playerClub = new ArrayList<FootballClub>();
        for (ClubPlayer clubPlayer : clubPlayers) {
            if (clubPlayer.getPlayer().getId().equals(id)) {
                playerClub.add(clubPlayer.getClub());
            }
        }
        return playerClub;
    }
    public ArrayList<FootballPlayer> findAllPlayerByClub(String id) {
        ArrayList<FootballPlayer> clubPlayer = new ArrayList<FootballPlayer>();
        for (ClubPlayer clubPlayer1 : clubPlayers) {
            if (clubPlayer1.getClub().getId().equals(id)) {
                clubPlayer.add(clubPlayer1.getPlayer());
            }
        }
        return clubPlayer;
    }
    public ArrayList<FootballPlayer> findAllAddedData() {
        ArrayList<FootballPlayer> playersWithoutClub = new ArrayList<>();
        for (FootballPlayer player : players) {
            boolean hasClub = false;
            for (ClubPlayer clubPlayer : clubPlayers) {
                if (clubPlayer.getPlayer().getId().equals(player.getId())) {
                    hasClub = true;
                    break;
                }
            }
            if (!hasClub) {
                playersWithoutClub.add(player);
            }
        }
        return playersWithoutClub;
    }
}