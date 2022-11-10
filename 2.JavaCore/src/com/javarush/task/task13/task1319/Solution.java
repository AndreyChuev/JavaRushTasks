package com.javarush.task.task13.task1319;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
        OutputStream output = new FileOutputStream(scanner.nextLine(), true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output))) {
            while (true) {
                String line = scanner.nextLine();
                if (!line.equals("exit")) {
                    writer.write(line + "\n");
                } else {
                    writer.write(line);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
