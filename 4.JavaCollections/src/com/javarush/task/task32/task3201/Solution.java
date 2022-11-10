package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(args[0]), "rw")){
            String text = args[2];
            if (Long.parseLong(args[1]) > raf.length() ) {
                raf.seek(raf.length());
            } else {
                raf.seek(Long.parseLong(args[1]));
            }
            raf.write(text.getBytes());
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
    }
}
