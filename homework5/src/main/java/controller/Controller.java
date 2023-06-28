package controller;
import dictionary.Dictionary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Controller {
    Dictionary<String, Integer> dictionary = new Dictionary<String, Integer>();
    Dictionary<String, Integer> dictionary1 = new Dictionary<String, Integer>();
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        System.out.println("          WELCOME TO THE DICTIONARY OF AUTOMOTIVE TERMS!");
        dictionary.put("Battery", 1);
        dictionary.put("Shock absorber", 2);
        dictionary.put("Bumper", 3);
        dictionary.put("Generator", 4);
        dictionary.put("Engine", 5);
        dictionary.put("Carburetor", 6);
        dictionary.put("Suspension", 7);
        dictionary.put("Bearing", 8);
        String choice;
        menu(dictionary);
        while ((choice = bufferedReader.readLine()) != null) {
            select(bufferedReader, choice);
        }
    }
    public void menu(Dictionary<String, Integer> dictionary) {
        System.out.println("                  -------------------------------");
        System.out.println("------------------ DICTIONARY OF AUTOMOTIVE TERMS ---------------------");
        System.out.println("                  -------------------------------");
        dictionary.printDictionary();
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("                      Please, make a choice:                          *");
        System.out.println("If you want to check the size of the dictionary, enter - 1            *");
        System.out.println("If you want to check if the dictionary is empty, enter - 2            *");
        System.out.println("If you want to know is dictionary contains some key, enter - 3        *");
        System.out.println("If you want to know is dictionary contains some value, enter - 4      *");
        System.out.println("If you want to get value by key, enter - 5                            *");
        System.out.println("If you want to put new value, enter - 6                               *");
        System.out.println("If you want to remove object by key, enter - 7                        *");
        System.out.println("If you want to put all (Dictionary<K, V> dictionary), enter - 8       *");
        System.out.println("If you want to clear dictionary, enter - 9                            *");
        System.out.println("If you want to get all keys, enter - 10                               *");
        System.out.println("If you want to get collection of values, enter - 11                   *");
        System.out.println("If you want to exit the application, enter - 0                        *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }
    private void select(BufferedReader bufferedReader, String choice) throws IOException {
        switch (choice) {
            case "1":
                size(bufferedReader);
                break;
            case "2":
                booleanIsEmpty(bufferedReader);
                break;
            case "3":
                booleanContainsKey(bufferedReader);
                break;
            case "4":
                booleanContainsValue(bufferedReader);
                break;
            case "5":
                getValue(bufferedReader);
                break;
            case "6":
                booleanPut(bufferedReader);
                break;
            case "7":
                booleanRemove(bufferedReader);
                break;
            case "8":
                booleanPutAll(bufferedReader);
                break;
            case "9":
                booleanClear(bufferedReader);
                break;
            case "10":
                keySet(bufferedReader);
                break;
            case "11":
                values(bufferedReader);
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("You have an error, please enter again");
        }
        menu(dictionary);
    }
    private void size(BufferedReader bufferedReader) {
        System.out.println("Size of dictionary: " + dictionary.size());
    }
    private void booleanIsEmpty(BufferedReader bufferedReader) {
        if (dictionary.isEmpty()) {
            System.out.println("Dictionary is empty.");
        } else {
            System.out.println("Dictionary is not empty.");
        }
    }
    private void booleanContainsKey(BufferedReader bufferedReader) {
        System.out.println("Enter key: ");
        try {
            String key = bufferedReader.readLine();
            if (dictionary.containsKey(key)) {
                System.out.println("The dictionary contains the key: " + key);
            } else {
                System.out.println("The dictionary does not contain the key: " + key);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
        }
    }
    private void booleanContainsValue(BufferedReader bufferedReader) {
        System.out.println("Enter value: ");
        try {
            int value = Integer.parseInt(bufferedReader.readLine());
            if (dictionary.containsValue(value)) {
                System.out.println("The dictionary contains the value: " + value);
            } else {
                System.out.println("The dictionary does not contain the value: " + value);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer value.");
        }
    }
    private void getValue(BufferedReader bufferedReader) {
        System.out.println("Enter key: ");
        try {
            String key = bufferedReader.readLine();
            Integer value = dictionary.get(key);
            if (value != null) {
                System.out.println("Value for key " + key + ":" + value);
            } else {
                System.out.println("No value found for key: " + key + ".");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
        }
    }
    private void booleanPut(BufferedReader bufferedReader) {
        System.out.println("Enter key: ");
        try {
            String key = bufferedReader.readLine();
            System.out.println("Enter value: ");
            int value = Integer.parseInt(bufferedReader.readLine());
            dictionary.put(key, value);
            System.out.println("Value added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer value.");
        }
    }
    private void booleanRemove(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter key: ");
        String key = bufferedReader.readLine();
        if (dictionary.remove(key)) {
            System.out.println("Removed");
        } else {
            System.out.println("Error");
        }
    }
    private void booleanPutAll(BufferedReader bufferedReader) {
        if (dictionary.putAll(dictionary1)) {
            System.out.println("Dictionary was updated.");
        } else {
            System.out.println("Provided dictionary is empty.");
        }
    }
    private void booleanClear(BufferedReader bufferedReader) {
        dictionary.clear();
        System.out.println("Dictionary is cleared.");
    }
    private void keySet(BufferedReader bufferedReader) {
        if (dictionary.keySet().isEmpty()) {
            System.out.println("The key set is empty.");
        } else {
            System.out.println("Keys in the dictionary: " + dictionary.keySet());
        }
    }
    private void values(BufferedReader bufferedReader) {
        if (dictionary.values().isEmpty()) {
            System.out.println("There are no values in the dictionary.");
        } else {
            System.out.println("Values in the dictionary: " + dictionary.values());
        }
    }
}