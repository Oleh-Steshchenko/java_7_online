package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
public class StreamController {
    public static void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Text Statistics");
        while (true) {
            System.out.println("Menu:");
            System.out.println("For typing text, please enter - 1");
            System.out.println("Exit, enter - 0");
            System.out.print("Please enter your choice: ");
            String choice = bufferedReader.readLine();
            switch (choice) {
                case "1"->processText(bufferedReader);
                case "0"-> System.exit(0);
                default->System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void processText(BufferedReader reader) throws IOException {
        String leftAlignFormat = "| %-15s | %-6d | %-6d | %-11d | %-10d  |%n";
        StringBuilder stringBuilder = new StringBuilder();
        String text;
        System.out.print("Enter your text: ");
        text = reader.readLine();
        if (!text.trim().isEmpty()) {
            stringBuilder.append(text).append(System.lineSeparator());
        }
        while (text.trim().isEmpty()) {
            System.out.println("Text cannot be empty. Please enter your text: ");
            text = reader.readLine();
        }
        String allTexts = stringBuilder.toString().trim();
        Map<String, Long> words = Arrays.stream(allTexts.split("[^\\p{L}]+"))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int totalWords = words.values().stream().mapToInt(Math::toIntExact).sum();
        final int[] rat = {1};
        printTableHeader();
        words.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> {
                    String word = entry.getKey();
                    long count = entry.getValue();
                    int percent = (int) Math.round(count * 100.0 / (double) totalWords);
                    int sentencePercentage = (int) Math.round(getWordSentencePercentage(word, allTexts) * 100.0);
                    System.out.format(leftAlignFormat, word, rat[0]++, count, percent, sentencePercentage);
                });
        printTableFooter();
    } private static double getWordSentencePercentage(String word, String text) {
        String[] sentences = text.split("[.!?]");
        int numSentences = sentences.length;
        long numSentencesWithWord = Arrays.stream(sentences)
                .filter(sentence -> sentence.toLowerCase().contains(word.toLowerCase()))
                .count();
        return (double) numSentencesWithWord / (double) numSentences;
    }
    private static void printTableHeader() {
        System.out.println("|-----------------|--------|--------|-------------|-------------|");
        System.out.println("|      Words      | Rating | Count  |%of the total|% in sentence|");
        System.out.println("|-----------------|--------|--------|-------------|-------------|");
    }
    private static void printTableFooter() {
        System.out.println("|---------------------------------------------------------------|");
    }
}