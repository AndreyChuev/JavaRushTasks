package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Замена чисел
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1924\test.txt
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(console.readLine()))) {
            ArrayList<String> lines = new ArrayList<>();
            while (reader.ready())
                lines.add(reader.readLine());

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                Matcher matcher = Pattern.compile("\\b\\d+\\b").matcher(line);
                while (matcher.find()) {
                     String num = matcher.group();
                     if (!map.containsKey(Integer.parseInt(num))) continue;
                     line = line.replaceFirst("\\b" + num + "\\b",map.get(Integer.parseInt(num)));
                }
                lines.set(i,line);
            }
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Усё пропало!");
            e.printStackTrace();
        }
    }
}
