package org.example;

import java.util.Scanner;
import java.io.Console;
import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value:");
        String number = scanner.nextLine();
        String numberOnly = number.replaceAll("[^0-9]", "");
        System.out.println(numberOnly);
        long num = Long.parseLong(numberOnly);
        long sum = 0;
        while (num >= 1) {
            long lastval = num % 10;
            num = num / 10;
            sum += lastval;
        }
        System.out.println(sum);
    }
}
