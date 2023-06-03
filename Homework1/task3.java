package org.example;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
public class task3 {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            System.out.print("\n" + "Write  the number of the lesson you want to learn about: ");
            int a = input.nextInt();
            a = a * 45 + (a / 2) * 5 + ((a + 1) / 2 - 1) * 15;
            System.out.println("The lesson ends at: " + (a / 60 + 9 + " " + a % 60));
        }
    }


