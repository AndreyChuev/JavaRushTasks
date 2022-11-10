package com.javarush.task.task19.task1926;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Перевертыши
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1926\input.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(console.readLine()))) {
            ArrayList<String> lines = new ArrayList<>();
            while (reader.ready())
                lines.add(new StringBuilder(reader.readLine()).reverse().toString());
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
    }
}
