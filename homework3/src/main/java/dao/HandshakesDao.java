package dao;
import java.util.Random;
public class HandshakesDao {
    private boolean[][] connections;
    private String[] people;
    private String[] path;
    private int pathIndex;
    public HandshakesDao(String[] people) {
        this.people = people;
        connections = new boolean[people.length][people.length];
    }
    public void buildRandomConnection() {
        Random random = new Random();
        int person1 = random.nextInt(people.length);
        int person2 = random.nextInt(people.length);
        while (person1 == person2 || connections[person1][person2]) {
            person1 = random.nextInt(people.length);
            person2 = random.nextInt(people.length);
        }
        connections[person1][person2] = true;
        connections[person2][person1] = true;
        System.out.println(people[person1] + " knows " + people[person2]);
    }
    public void showConnections() {
        System.out.println("Connections:");
        for (int i = 0; i < people.length; i++) {
            System.out.print(people[i] + " knows: ");
            boolean isFirst = true;
            for (int j = 0; j < people.length; j++) {
                if (connections[i][j]) {
                    if (!isFirst) {
                        System.out.print(", ");
                    }
                    System.out.print(people[j]);
                    isFirst = false;
                }
            }
            System.out.println();
        }
    }
    public String[] getPath() {
        String[] trimmedPath = new String[pathIndex];
        System.arraycopy(path, 0, trimmedPath, 0, pathIndex);
        return trimmedPath;
    }
    public void findPath(String person1, String person2) {
        resetPath();
        int startIndex = getIndex(person1);
        int endIndex = getIndex(person2);
        if (startIndex != -1 && endIndex != -1) {
            depthFirstSearch(startIndex, endIndex, new boolean[people.length]);
        }
    }
    private void resetPath() {
        path = new String[people.length];
        pathIndex = 0;
    }
    private int getIndex(String person) {
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals(person)) {
                return i;
            }
        }
        return -1;
    }
    private void depthFirstSearch(int currentIndex, int endIndex, boolean[] visited) {
        visited[currentIndex] = true;
        path[pathIndex] = people[currentIndex];
        pathIndex++;
        if (currentIndex == endIndex) {
            return;
        }
        for (int i = 0; i < people.length; i++) {
            if (connections[currentIndex][i] && !visited[i]) {
                depthFirstSearch(i, endIndex, visited);
                if (path[pathIndex - 1].equals(people[endIndex])) {
                    return;
                }
                pathIndex--;
            }
        }
    }
}