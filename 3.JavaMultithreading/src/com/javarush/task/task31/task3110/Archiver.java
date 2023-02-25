package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {

    public static void main(String[] args) throws Exception {
        ZipFileManager zipFileManager = new ZipFileManager(requestPath("Archive path: "));
        zipFileManager.createZip(requestPath("File path: "));
    }

    private static Path requestPath(String msg) throws IOException {
        System.out.printf(msg);
        return Paths.get(ConsoleHelper.readString());
    }
}
