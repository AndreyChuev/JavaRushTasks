package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        try (Scanner console = new Scanner(System.in)) {
            Path path = Paths.get(console.nextLine());
            if (!Files.isDirectory(path))
                throw new IllegalArgumentException(path + " - не папка");

            SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
            Files.walkFileTree(path,searchFileVisitor);
            System.out.println("Всего папок - " + searchFileVisitor.getFolderCount());
            System.out.println("Всего файлов - " + searchFileVisitor.getFileCount());
            System.out.println("Общий размер - " + searchFileVisitor.getDirectorySize());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
