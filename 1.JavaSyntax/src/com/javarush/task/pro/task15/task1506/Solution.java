package com.javarush.task.pro.task15.task1506;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/* 
Фейсконтроль
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        List<String> lines = Files.readAllLines(Paths.get(scanner.nextLine()));

        String[] sign = {".",",", " "};

        ArrayList<String> result = new ArrayList<>();

        for (String line : lines) {
            StringBuilder stringBuilder = new StringBuilder(line);
            for (int i = 0; i < sign.length; i++) {
                while (stringBuilder.indexOf(sign[i]) > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.indexOf(sign[i]));
                }
            }
            result.add(stringBuilder.toString());
        }

        for (String s : result) {
            System.out.println(s);
        }
    }
}

