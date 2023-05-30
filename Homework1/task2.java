package org.example;
import java.util.Scanner;
public class task2 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a value:");
            String Char = scanner.nextLine();
            String Only = Char.replaceAll("[^a-zA-Z]", "");
            System.out.println(Only.toUpperCase());
            String str = Only.toUpperCase();
            int frequency[] = new int[128];
            for (int i = 0; i < str.length(); i++) {
                frequency[(int) str.charAt(i)]++;
            }
            for (int i = 0; i < frequency.length; i++) {
                if (frequency[i] != 0) {
                    System.out.println((char) i + " - " + frequency[i]);

                }

            }
        }
    }
