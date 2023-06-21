package controller;
import dao.HandshakesDao;
import java.io.IOException;
import java.util.Scanner;
public class HandshakesController {
    public static void start() throws IOException {
        String[] people = {"Vlad", "Max", "Kolya", "Oleg", "Dima", "Slava","Egor"};
        HandshakesDao connectionBuilder = new HandshakesDao(people);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Build Random Connection");
            System.out.println("2. Show Connections");
            System.out.println("3. Find Path");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    connectionBuilder.buildRandomConnection();
                    break;
                case 2:
                    connectionBuilder.showConnections();
                    break;
                case 3:
                    System.out.print("Enter the names of 2 people (in format name1-name2): ");
                    String[] names = scanner.nextLine().split("-");
                    String person1 = names[0];
                    String person2 = names[1];
                    connectionBuilder.findPath(person1, person2);
                    String[] path = connectionBuilder.getPath();
                    if (path.length > 0) {
                        System.out.println("Path between " + person1 + " and " + person2 + ":");
                        for (String name : path) {
                            System.out.println(name);
                        }
                    } else {
                        System.out.println("No path found between " + person1 + " and " + person2 + ".");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}