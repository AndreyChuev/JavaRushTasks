package com.javarush.task.task32.task3210;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            String text = args[2];
            byte[] buffer = new byte[text.getBytes().length];

            raf.seek(Long.parseLong(args[1]));
            raf.read(buffer, 0, text.getBytes().length);
            String readText = new String(buffer);

            boolean result = text.equals(readText);

            raf.seek(raf.length());
            raf.write(String.valueOf(result).getBytes());
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
    }
}
