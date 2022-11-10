package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();
        Files.walkFileTree(Paths.get(root),searchFileVisitor);
        return searchFileVisitor.getPaths();
    }

    public static void main(String[] args) {
        try {
            List<String> res = getFileTree("C:\\Games");
            for (String re : res) {
                System.out.println(re);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
