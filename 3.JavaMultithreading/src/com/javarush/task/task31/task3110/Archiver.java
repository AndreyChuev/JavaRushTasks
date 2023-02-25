package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {

    public static void main(String[] args) throws Exception {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))){
            ZipFileManager zipFileManager = new ZipFileManager(requestPath("Archive path: ", consoleReader));
            zipFileManager.createZip(requestPath("File path: ", consoleReader));
        }
    }

    private static Path requestPath(String msg, BufferedReader consoleReader) throws IOException {
        System.out.printf(msg);
        return Paths.get(consoleReader.readLine());
    }
}
