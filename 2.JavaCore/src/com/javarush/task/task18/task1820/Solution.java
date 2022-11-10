package com.javarush.task.task18.task1820;

import java.io.*;
import java.util.ArrayList;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader = new BufferedReader(new FileReader(consoleReader.readLine()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(consoleReader.readLine()))) {
            String[] numbers = reader.readLine().split(" ");
            String result = "";
            for (int i = 0; i < numbers.length; i++) {
                int num = (int) Math.round(Double.parseDouble(numbers[i]));
                result += num + " ";
            }
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
