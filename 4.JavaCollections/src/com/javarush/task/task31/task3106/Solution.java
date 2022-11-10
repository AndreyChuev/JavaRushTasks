package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
C:\Users\Андрей\Desktop\Files\file.zip.001
C:\Users\Андрей\Desktop\Files\file.zip.002
C:\Users\Андрей\Desktop\Files\file.zip.004
C:\Users\Андрей\Desktop\Files\file.zip.005
C:\Users\Андрей\Desktop\Files\file.zip.003
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        List<FileInputStream> paths = new ArrayList<>();
        TreeSet<String> files = new TreeSet<>(Arrays.asList(args).subList(1, args.length));
        for (String file : files) {
            paths.add(new FileInputStream(file));
        }
        try (FileOutputStream outputStream = new FileOutputStream(args[0]);
             ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(paths)))){
            while (zipInputStream.getNextEntry() != null) {
                while (zipInputStream.available() != 0) {
                    byte[] buffer = new byte[1024];
                    int readByte = zipInputStream.read(buffer);
                    if (readByte != -1) outputStream.write(buffer, 0, readByte);
                }
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
