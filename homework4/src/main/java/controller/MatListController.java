package controller;
import mathlist.MatList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class MatListController {
    private static MatList<Double> matList = new MatList<>();
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String select;
        menu();
        while ((select = bufferedReader.readLine()) != null) {
            choice(bufferedReader, select);
        }
    }
    private void choice(BufferedReader bufferedReader, String select) throws IOException {
        switch (select) {
            case "0" -> System.exit(0);
            case "1" -> addNumber(bufferedReader);
            case "2" -> addNumbers(bufferedReader);
            case "3" -> joinMatLists(bufferedReader);
            case "4" -> intersectMatLists(bufferedReader);
            case "5" -> sortDescending(bufferedReader);
            case "6" -> sortDescendingRange(bufferedReader);
            case "7" -> sortDescendingValue(bufferedReader);
            case "8" -> sortAscending(bufferedReader);
            case "9" -> sortAscendingRange(bufferedReader);
            case "10" -> sortAscendingValue(bufferedReader);
            case "11" -> getNumberAtIndex(bufferedReader);
            case "12" -> getMaxNumber(bufferedReader);
            case "13" -> getMinNumber(bufferedReader);
            case "14" -> getAverageNumber(bufferedReader);
            case "15" -> getMedianNumber(bufferedReader);
            case "16" -> convertToArray(bufferedReader);
            case "17" -> convertToArrayRange(bufferedReader);
            case "18" -> cutMatList(bufferedReader);
            case "19" -> clearMatList(bufferedReader);
            case "20" -> clearNumbers(bufferedReader);
            default -> System.out.println("Invalid choice. Please try again.");
        }
        menu();
    }
    private static void menu() {
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|                                                          APPLICATIONS OF MATHEMATICAL METHODS                                                                               |");
        System.out.println("| 0 - Quit | 1 - Add a number | 2 - Add numbers | 3 - Join MatLists | 4 - Intersect MatLists | 5 - Sort in descending order | 6 - Sort in descending order (range)            |");
        System.out.println("| 7 - Sort in descending order (value) | 8 - Sort in ascending order | 9 - Sort in ascending order (range) | 10 - Sort in ascending order (value) | 11 - Get number at index  |");
        System.out.println("| 12 - Get maximum number | 13 - Get minimum number | 14 - Get average number | 15 - Get median number | 16 - Convert MatList to array                                        |");
        System.out.println("| 17 - Convert MatList to array (range) | 18 - Cut MatList | 19 - Clear MatList | 20 - Clear specific numbers from MatList                                                    |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("Enter your choice:");
    }
    private static void addNumber(BufferedReader bufferedReader) throws IOException {
        System.out.print("Enter a number: ");
        try {
            double number = Double.parseDouble(bufferedReader.readLine());
            matList.add(number);
            System.out.println("Number added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
    private static void addNumbers(BufferedReader bufferedReader) throws IOException {
        System.out.print("Enter numbers (space-separated): ");
        String[] numbersStr = bufferedReader.readLine().split(" ");
        Double[] numbers = new Double[numbersStr.length];
        boolean validInput = true;
        for (int i = 0; i < numbersStr.length; i++) {
            try {
                numbers[i] = Double.parseDouble(numbersStr[i]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
                validInput = false;
                break;
            }
        }
        if (validInput) {
            matList.add(numbers);
            System.out.println("Numbers added successfully.");
        }
    }
    private static void joinMatLists(BufferedReader bufferedReader) throws IOException {
        System.out.print("Enter the number of MatLists to join: ");
        try {
            int numLists = Integer.parseInt(bufferedReader.readLine());

            if (numLists <= 0) {
                System.out.println("Invalid input. The number of MatLists should be greater than 0.");
                return;
            }
            @SuppressWarnings("unchecked")
            MatList<Double>[] matLists = (MatList<Double>[]) new MatList[numLists];
            boolean validInput = true;
            for (int i = 0; i < numLists; i++) {
                System.out.println("Enter MatList " + (i + 1) + ":");
                matLists[i] = readMatList(bufferedReader);
                if (matLists[i].toArray().length == 0) {
                    System.out.println("Invalid MatList. It is empty.");
                    validInput = false;
                    break;
                }
            }
            if (validInput) {
                matList.join(matLists);
                System.out.println("MatLists joined successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number of MatLists.");
        }
    }
    private static void intersectMatLists(BufferedReader bufferedReader) throws IOException {
        System.out.print("Enter the number of MatLists to intersect: ");
        try {
            int numLists = Integer.parseInt(bufferedReader.readLine());
            MatList<Double>[] matLists = new MatList[numLists];
            boolean invalidInput = false;
            for (int i = 0; i < numLists; i++) {
                matLists[i] = readMatList(bufferedReader);
                if (matLists[i].isEmpty()) {
                    System.out.println("Empty MatList provided. Please enter a non-empty MatList.");
                    invalidInput = true;
                    break;
                }
            }
            if (!invalidInput) {
                matList.intersection(matLists);
                System.out.println("MatLists intersected successfully.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number of MatLists.");
        }
    }
    private static void sortDescending(BufferedReader bufferedReader) {
        if (matList.toArray().length <= 1) {
            System.out.println("Sorting a single element or an empty list is not possible.");
            return;
        }
        try {
            matList.sortDesc();
            System.out.println("MatList sorted in descending order: " + Arrays.toString(matList.toArray()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }
    private static void sortDescendingRange(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to sort.");
            return;
        }
        try {
            System.out.print("Enter the first index: ");
            int firstIndex = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the last index: ");
            int lastIndex = Integer.parseInt(bufferedReader.readLine());

            if (firstIndex < 0 || lastIndex >= matList.toArray().length || firstIndex >= lastIndex) {
                System.out.println("Invalid index range. Please provide valid indices.");
                return;
            }
            matList.sortDesc(firstIndex, lastIndex);
            System.out.println("MatList sorted in descending order (range): " + Arrays.toString(matList.toArray()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid indices.");
        }
    }
    private static void sortDescendingValue(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to sort.");
            return;
        }
        try {
            System.out.print("Enter a value: ");
            double value = Double.parseDouble(bufferedReader.readLine());
            boolean valueExists = false;
            for (int i = 0; i < matList.toArray().length; i++) {
                if (matList.get(i) != null && matList.get(i).doubleValue() == value) {
                    valueExists = true;
                    break;
                }
            }
            if (!valueExists) {
                System.out.println("Value not found in MatList.");
                return;
            }
            matList.sortDesc(value);
            System.out.println("MatList sorted in descending order (value): " + Arrays.toString(matList.toArray()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid value.");
        }
    }
    private static void sortAscending(BufferedReader bufferedReader) {
        if (matList.toArray().length <= 1) {
            System.out.println("Sorting a single element or an empty list is not possible.");
            return;
        }
        try {
            matList.sortAsc();
            System.out.println("MatList sorted in ascending order: " + Arrays.toString(matList.toArray()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }
    private static void sortAscendingRange(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to sort.");
            return;
        }
        try {
            System.out.print("Enter the first index: ");
            int firstIndex = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the last index: ");
            int lastIndex = Integer.parseInt(bufferedReader.readLine());

            if (firstIndex < 0 || lastIndex >= matList.toArray().length || firstIndex >= lastIndex) {
                System.out.println("Invalid index range. Please provide valid indices.");
                return;
            }
            matList.sortAsc(firstIndex, lastIndex);
            System.out.println("MatList sorted in ascending order (range): " + Arrays.toString(matList.toArray()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid indices.");
        }
    }
    private static void sortAscendingValue(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to sort.");
            return;
        }
        try {
            System.out.print("Enter a value: ");
            double value = Double.parseDouble(bufferedReader.readLine());
            boolean valueExists = false;
            for (int i = 0; i < matList.toArray().length; i++) {
                if (matList.get(i) != null && matList.get(i).doubleValue() == value) {
                    valueExists = true;
                    break;
                }
            }
            if (!valueExists) {
                System.out.println("Value not found in MatList.");
                return;
            }
            matList.sortAsc(value);
            System.out.println("MatList sorted in ascending order (value): " + Arrays.toString(matList.toArray()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid value.");
        }
    }
    private static void getNumberAtIndex(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to retrieve.");
            return;
        }
        try {
            System.out.print("Enter the index: ");
            int index = Integer.parseInt(bufferedReader.readLine());

            if (index < 0 || index >= matList.toArray().length) {
                System.out.println("Invalid index. Index should be between 0 and " + (matList.toArray().length - 1) + ".");
                return;
            }
            if (index == matList.toArray().length - 1) {
                System.out.println("Cannot retrieve number at the last index.");
                return;
            }
            Number number = matList.get(index);
            System.out.println("Number at index " + index + ": " + number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid index.");
        }
    }
    private static void getMaxNumber(BufferedReader bufferedReader) {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to retrieve.");
            return;
        }
        Number max = matList.getMax();
        System.out.println("Maximum number: " + max);
    }
    private static void getMinNumber(BufferedReader bufferedReader) {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to retrieve.");
            return;
        }
        Number min = matList.getMin();
        System.out.println("Minimum number: " + min);
    }
    private static void getAverageNumber(BufferedReader bufferedReader) {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to retrieve.");
            return;
        }
        Number average = matList.getAverage();
        System.out.println("Average number: " + average);
    }
    private static void getMedianNumber(BufferedReader bufferedReader) {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to retrieve.");
            return;
        }
        Number median = matList.getMedian();
        System.out.println("Median number: " + median);
    }
    private static void convertToArray(BufferedReader bufferedReader) {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to convert to an array.");
            return;
        }
        Number[] array = matList.toArray();
        System.out.println("MatList converted to array: " + Arrays.toString(array));
    }
    private static void convertToArrayRange(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to convert to an array (range).");
            return;
        }
        try {
            System.out.print("Enter the first index: ");
            int firstIndex = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the last index: ");
            int lastIndex = Integer.parseInt(bufferedReader.readLine());
            if (firstIndex < 0 || lastIndex >= matList.toArray().length || firstIndex > lastIndex) {
                System.out.println("Invalid range. Please provide valid indices.");
                return;
            }
            Number[] array = matList.toArray(firstIndex, lastIndex);
            System.out.println("MatList converted to array (range): " + Arrays.toString(array));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid indices.");
        }
    }
    private static void cutMatList(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to cut.");
            return;
        }
        try {
            System.out.print("Enter the first index: ");
            int firstIndex = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the last index: ");
            int lastIndex = Integer.parseInt(bufferedReader.readLine());
            if (firstIndex < 0 || lastIndex >= matList.toArray().length || firstIndex >= lastIndex) {
                System.out.println("Invalid range. Please provide valid indices.");
                return;
            }
            MatList<Double> cutList = matList.cut(firstIndex, lastIndex);
            System.out.println("Cut elements: " + Arrays.toString(cutList.toArray()));
            matList = cutList;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid indices.");
        }
    }
    private static void clearMatList(BufferedReader bufferedReader) {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to clear.");
            return;
        }
        matList.clear();
        System.out.println("MatList cleared.");
    }
    private static void clearNumbers(BufferedReader bufferedReader) throws IOException {
        if (matList.toArray().length == 0) {
            System.out.println("MatList is empty. No data to clear numbers from.");
            return;
        }
        System.out.print("Enter numbers to clear (space-separated): ");
        String[] numbersStr = bufferedReader.readLine().split(" ");
        Double[] numbers = new Double[numbersStr.length];
        try {
            for (int i = 0; i < numbersStr.length; i++) {
                numbers[i] = Double.parseDouble(numbersStr[i]);
            }
            boolean validNumbers = true;
            for (Double number : numbers) {
                if (!matList.contains(number)) {
                    validNumbers = false;
                    break;
                }
            }
            if (validNumbers) {
                matList.clear(numbers);
                System.out.println("Numbers cleared from MatList.");
            } else {
                System.out.println("The specified numbers do not exist in the MatList.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
        }
    }
    private static MatList<Double> readMatList(BufferedReader bufferedReader) throws IOException {
        MatList<Double> list = new MatList<>();
        try {
            System.out.print("Enter the number of elements in the MatList: ");
            int size = Integer.parseInt(bufferedReader.readLine());
            if (size <= 0) {
                System.out.println("Invalid size. The size should be greater than 0.");
                return list;
            }
            System.out.print("Enter the elements (space-separated): ");
            String[] elementsStr = bufferedReader.readLine().split(" ");

            if (elementsStr.length != size) {
                System.out.println("Number of elements entered does not match the specified size.");
                return list;
            }
            for (int i = 0; i < size; i++) {
                try {
                    double element = Double.parseDouble(elementsStr[i]);
                    list.add(element);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter valid numeric elements.");
                    return list;
                }
            }
            return list;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
            return list;
        }
    }
}