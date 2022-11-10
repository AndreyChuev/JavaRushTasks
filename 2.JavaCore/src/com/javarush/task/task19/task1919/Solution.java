package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1919\persons.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            TreeMap<String, Double> persons = new TreeMap<>();
            while (reader.ready()) {
                String[] info = reader.readLine().split(" ");
                if (persons.containsKey(info[0])) {
                    persons.put(info[0], persons.get(info[0]) + Double.parseDouble(info[1]));
                } else {
                    persons.put(info[0], Double.parseDouble(info[1]));
                }
            }
            for (Map.Entry<String, Double> stringDoubleEntry : persons.entrySet()) {
                System.out.println(stringDoubleEntry.getKey() + " " + stringDoubleEntry.getValue());
            }
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
    }
}
