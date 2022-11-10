package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1922\textFile.txt
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(console.readLine()))) {
            ArrayList<String> lines = new ArrayList<>();
            while (reader.ready()) {
                String line = reader.readLine();
                String[] wordArray = line.split("\\s");
                int count = 0;
                for (String s : wordArray) {
                    if (words.contains(s)) {
                        count++;
                    }
                }
                if (count == 2) {
                    lines.add(line);
                }
            }
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
    }
}
