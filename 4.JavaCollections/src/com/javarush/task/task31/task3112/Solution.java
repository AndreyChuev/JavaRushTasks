package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        String name = urlString.substring(urlString.lastIndexOf("/") + 1);
        try {
            Files.createDirectory(downloadDirectory);
            Files.createFile(Paths.get(downloadDirectory + "/" + name));
        } catch (FileAlreadyExistsException e) {
        }
        Path path = Files.createTempFile(name, null);
        Path targetDirectory = null;
        try (InputStream input = url.openStream()) {
            int available = input.available();
            if (available == Files.copy(input,path, REPLACE_EXISTING)) {
                targetDirectory = Files.move(path, Paths.get(downloadDirectory + "/" + name), REPLACE_EXISTING) ;
            }
        }
        return targetDirectory;
    }
}
