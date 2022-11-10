package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

/* 
Самый богатый
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1920\persons.txt
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
             Map.Entry<String, Double> entry = persons
                     .entrySet()
                     .stream()
                     .max(Map.Entry.comparingByValue())
                     .get();

             Set<Map.Entry<String, Double>> set = persons
                     .entrySet()
                     .stream()
                     .filter(e -> e.getValue() >= entry.getValue())
                     .collect(Collectors.toSet());


             TreeMap<String, Double> map = new TreeMap<>();
             for (Map.Entry<String, Double> stringDoubleEntry : set) {
                 map.put(stringDoubleEntry.getKey(), stringDoubleEntry.getValue());
             }

             for (Map.Entry<String, Double> stringDoubleEntry : map.entrySet()) {
                 System.out.println(stringDoubleEntry.getKey());
             }



//            Map.Entry<String, Double> entry = null;
//            for (Map.Entry<String, Double> sde : persons.entrySet()) {
//                if (entry == null) entry = sde;
//                entry = sde.getValue() >= entry.getValue() ? sde : entry;
//            }
//            System.out.println(entry.getKey() + " " + entry.getValue());
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
    }
}
