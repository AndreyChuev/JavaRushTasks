package com.javarush.task.pro.task15.task1507;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/* 
Пропускаем не всех
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Path patch = Paths.get(scanner.nextLine());

        List<String> lines = Files.readAllLines(patch);

        int count = 0;
        for (String line : lines) {
            if (count % 2 == 0)
                System.out.println(line);
            ++count;
        }


    }
}

