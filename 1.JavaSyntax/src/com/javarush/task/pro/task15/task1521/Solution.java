package com.javarush.task.pro.task15.task1521;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* 
Временное сохранение файла
*/

public class Solution {
    private static Path dir = Path.of("C:\\Users\\Андрей\\Desktop");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        URL url = new URL(line);
        try (var input = url.openStream()){
            byte[] buffer = input.readAllBytes();
            Files.write(Files.createTempFile(null,null),buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}