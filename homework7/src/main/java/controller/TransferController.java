package controller;
import entity.FootballClub;
import entity.FootballPlayer;
import service.TransferService;
import javax.naming.InvalidNameException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
public class TransferController {
    TransferService transferService = new TransferService();
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("WELCOME TO THE FOOTBALL APP");
        System.out.println("Make a choice");
        String select;
        menu();
        while ((select = bufferedReader.readLine()) != null) {
            crud(bufferedReader, select);
        }
    }
    public void menu() {
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                              FOOTBALL APP                                                             |");
        System.out.println("| If you want to create a footballer, enter - 1              If you want to update a footballer, enter - 2              |");
        System.out.println("| If you want to delete a footballer, enter - 3              If you want to find a footballer by id, enter - 4          |");
        System.out.println("| If you want to find all footballers, enter - 5             If you want to see all the players in the club, enter - 6  |");
        System.out.println("| If you want to create a club, enter - 7                    If you want to update a club, enter - 8                    |");
        System.out.println("| If you want to delete a club, enter - 9                    If you want to find a club, enter - 10                     |");
        System.out.println("| If you want to find all clubs, enter - 11                  If you want to add a player to a club, enter - 12          |");
        System.out.println("| Check which club the player is in, enter - 13              If you want to see all added data enter - 14               |");
        System.out.println("| If you want to exit the program, enter - 0                                                                            |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
    }
    private void crud(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "1" -> createPlayer(bufferedReader);
            case "2" -> updatePlayer(bufferedReader);
            case "3" -> deletePlayer(bufferedReader);
            case "4" -> findPlayer(bufferedReader);
            case "5" -> findAllPlayer(bufferedReader);
            case "6" -> findAllPlayerByClub(bufferedReader);
            case "7" -> createClub(bufferedReader);
            case "8" -> updateClub(bufferedReader);
            case "9" -> deleteClub(bufferedReader);
            case "10" -> findClub(bufferedReader);
            case "11" -> findAllClubs(bufferedReader);
            case "12" -> addPlayerToClub(bufferedReader);
            case "13" -> findClubByPlayer(bufferedReader);
            case "14" -> findAllData(bufferedReader);
            case "0" -> System.exit(0);
        }
        menu();
    }
    private void addPlayerToClub(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter player ID: ");
            String playerId = bufferedReader.readLine();
            if (playerId.isEmpty()) {
                throw new IllegalArgumentException("Player ID cannot be empty.");
            }
            System.out.println("Please enter club ID: ");
            String clubId = bufferedReader.readLine();
            if (clubId.isEmpty()) {
                throw new IllegalArgumentException("Club ID cannot be empty.");
            }
            transferService.addPlayerToClub(playerId, clubId);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for player ID and club ID.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Player ID or club ID does not exist.");
        }
    }
    private void findAllPlayerByClub(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter player ID: ");
            String id = bufferedReader.readLine();
            validateNumber(id);
            FootballClub club = transferService.findByIdClub(id);
            if (club == null) {
                System.out.println("Club not found.");
                return;
            }
            ArrayList<FootballPlayer> allPlayers = transferService.findAllPlayerByClub(id);
            if (allPlayers.isEmpty()) {
                System.out.println("No players in this club.");
            } else {
                System.out.println(club);
                for (FootballPlayer footballPlayer : allPlayers) {
                    System.out.println(footballPlayer);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for the player ID.");
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Club ID does not exist.");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException occurred. Please check the code and input.");
        }
    }
    private void validateNumber(String input) throws NumberFormatException {
        if (!input.matches("\\d+")) {
            throw new NumberFormatException();
        }
    }
    private void findClubByPlayer(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter club ID: ");
            String id = bufferedReader.readLine();
            validateNumber(id);
            FootballPlayer player = transferService.findByIdPlayer(id);
            if (player == null) {
                System.out.println("Player not found.");
                return;
            }
            ArrayList<FootballClub> allClubs = transferService.findAllClubByPlayer(id);
            System.out.println(player);
            if (allClubs.isEmpty()) {
                System.out.println("Player is not part of any club.");
            } else {
                for (FootballClub footballClub : allClubs) {
                    System.out.println(footballClub);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for the club ID.");
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Player ID does not exist.");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException occurred. Please check the code and input.");
        }
    }
    private void createPlayer(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter player's first name:");
            String firstName = bufferedReader.readLine();
            validateName(firstName);
            System.out.println("Please enter player's last name:");
            String lastName = bufferedReader.readLine();
            validateName(lastName);
            System.out.println("Please enter player's age:");
            String ageString = bufferedReader.readLine();
            if (!ageString.matches("\\d+")) {
                throw new NumberFormatException("Invalid age input. Please enter a valid number.");
            }
            int age = Integer.parseInt(ageString);
            FootballPlayer footballPlayer = new FootballPlayer();
            footballPlayer.setFirstName(firstName);
            footballPlayer.setLastName(lastName);
            footballPlayer.setAge(age);
            transferService.createPlayer(footballPlayer);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createClub(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter club name: ");
            String nameClub = bufferedReader.readLine();
            if (nameClub.isEmpty()) {
                throw new IllegalArgumentException("Club name cannot be empty. Please enter a valid name.");
            }
            if (!nameClub.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid club name. Please enter only alphabetical characters.");
            }
            FootballClub club = new FootballClub();
            club.setName(nameClub);
            transferService.createClub(club);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void validateName(String name) throws InvalidNameException {
        if (!name.matches("[a-zA-Z]+")) {
            throw new InvalidNameException("Invalid input. Please enter only alphabetical characters for the name.");
        }
    }
    private void printListOfClub() {
        ArrayList<FootballClub> clubs = transferService.getClubs();
        for (int i = 0; i < clubs.size(); i++) {
            System.out.println((i + 1) + " - " + clubs.get(i));
        }
    }
    private void printListOfPlayers() {
        ArrayList<FootballPlayer> players = transferService.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + " - " + players.get(i));
        }
    }
    private void updatePlayer(BufferedReader bufferedReader) {
        try {
            printListOfPlayers();
            System.out.println("Please select a player to update: ");
            String choiceString = bufferedReader.readLine();
            if (!choiceString.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid player number.");
                return;
            }
            int choice = Integer.parseInt(choiceString);
            if (choice < 1 || choice > transferService.getPlayers().size()) {
                throw new IllegalArgumentException("Invalid player number. Please select a valid player.");
            }
            System.out.println("Please enter player first name: ");
            String firstName = bufferedReader.readLine();
            validateName(firstName);
            System.out.println("Please enter player's last name: ");
            String lastName = bufferedReader.readLine();
            validateName(lastName);
            System.out.println("Please enter player's age: ");
            String ageString = bufferedReader.readLine();
            if (!ageString.matches("\\d+")) {
                throw new NumberFormatException("Invalid age input. Please enter a valid number.");
            }
            int age = Integer.parseInt(ageString);
            FootballPlayer footballPlayer = new FootballPlayer();
            footballPlayer.setFirstName(firstName);
            footballPlayer.setLastName(lastName);
            footballPlayer.setAge(age);
            transferService.updatePlayer(choice - 1, footballPlayer);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void deletePlayer(BufferedReader bufferedReader) {
        try {
            printListOfPlayers();
            System.out.println("Please enter player number to delete it: ");
            String choiceString = bufferedReader.readLine();
            if (!choiceString.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid player number.");
                return;
            }
            int choice = Integer.parseInt(choiceString);
            if (choice < 1 || choice > transferService.getPlayers().size()) {
                throw new IllegalArgumentException("Invalid player number. Please select a valid player.");
            }
            transferService.deletePlayer(choice - 1);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid player number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void findPlayer(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter player ID: ");
            String id = bufferedReader.readLine();
            if (id == null) {
                throw new NullPointerException("Player ID cannot be null.");
            }
            if (!id.matches("[a-zA-Z0-9]+")) {
                throw new NumberFormatException("Invalid player ID format. Please enter a valid ID.");
            }
            FootballPlayer footballPlayer = transferService.findByIdPlayer(id);
            if (footballPlayer == null) {
                System.out.println("Player not found.");
            } else {
                System.out.println("Player: " + footballPlayer);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    private void findAllPlayer(BufferedReader bufferedReader) {
        ArrayList<FootballPlayer> players = transferService.getPlayers();
        for (FootballPlayer player : players) {
            System.out.println(player);
        }
    }
    private void updateClub(BufferedReader bufferedReader) {
        try {
            printListOfClub();
            System.out.println("Please select a club to update it: ");
            String choiceString = bufferedReader.readLine();
            if (!choiceString.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid club number.");
                return;
            }
            int choice = Integer.parseInt(choiceString);
            if (choice < 1 || choice > transferService.getClubs().size()) {
                throw new IndexOutOfBoundsException("Invalid club number. Please select a valid club.");
            }
            System.out.println("Please enter club name: ");
            String nameClub = bufferedReader.readLine();
            if (nameClub.isEmpty()) {
                throw new IllegalArgumentException("Club name cannot be empty. Please enter a valid name.");
            }
            if (!nameClub.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Invalid club name. Please enter only alphabetical characters.");
            }
            FootballClub club = new FootballClub();
            club.setName(nameClub);
            transferService.updateClub(choice - 1, club);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid club number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private void deleteClub(BufferedReader bufferedReader) {
        try {
            printListOfClub();
            System.out.println("Please select the number of the club you want to delete: ");
            String choiceString = bufferedReader.readLine();
            if (!choiceString.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a valid club number.");
                return;
            }
            int choice = Integer.parseInt(choiceString);
            if (choice < 1 || choice > transferService.getClubs().size()) {
                throw new IndexOutOfBoundsException("Invalid club number. Please select a valid club.");
            }
            transferService.deleteClub(choice - 1);
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid club number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
    private void findClub(BufferedReader bufferedReader) {
        try {
            System.out.println("Please enter club ID: ");
            String id = bufferedReader.readLine();
            if (id == null) {
                throw new NullPointerException("Invalid input. Club ID cannot be null.");
            }
            FootballClub club = transferService.findByIdClub(id);
            if (club == null) {
                System.out.println("Club not found.");
            } else {
                System.out.println("Club: " + club);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid club ID.");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    private void findAllClubs(BufferedReader bufferedReader) {
        ArrayList<FootballClub> clubs = transferService.getClubs();
        for (FootballClub club : clubs) {
            System.out.println(club);
        }
    }
    private void findAllData(BufferedReader bufferedReader) {
        ArrayList<FootballClub> clubs = transferService.getClubs();
        for (FootballClub club : clubs) {
            System.out.println("Club: " + club);
            System.out.println("Players:");
            ArrayList<FootballPlayer> clubPlayers = transferService.findAllPlayerByClub(club.getId());
            if (clubPlayers.isEmpty()) {
                System.out.println("No players in this club.");
            } else {
                for (FootballPlayer player : clubPlayers) {
                    System.out.println(player);
                }
            }
            System.out.println("--------------------");
        }
        System.out.println("Players without a club:");
        ArrayList<FootballPlayer> playersWithoutClub = transferService.findAllAddedData();
        if (playersWithoutClub.isEmpty()) {
            System.out.println("All players are assigned to a club.");
        } else {
            for (FootballPlayer player : playersWithoutClub) {
                System.out.println(player);
            }
        }
    }
}