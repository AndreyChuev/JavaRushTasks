package com.javarush.task.task18.task1807;

import java.io.*;

/* 
Подсчет запятых
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             InputStream input = new FileInputStream(reader.readLine())) {
            int count = 0;
            char c = 44;
            while (input.available() > 0) {
                int b = input.read();
                if (b == c)
                    count++;
            }
            System.out.println(count);

        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            e.printStackTrace();
        }


    }
}
