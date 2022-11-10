package com.javarush.task.task13.task1318;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
             InputStream inputStream = new FileInputStream(scanner.nextLine());
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            reader.lines().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}