package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(console.readLine()))) {
            int requiredId = Integer.parseInt(args[0]);
            while (reader.ready()) {
                String line = reader.readLine();
                if (requiredId == getId(line))
                    System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getId(String line) {
        return Integer.parseInt(line.split(" ")[0].trim());
    }
}
