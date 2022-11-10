package com.javarush.task.task18.task1817;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
            String str = "";
            while (reader.ready()) {
                str += reader.readLine();
            }
            double spaces = 0d;
            Matcher matcher = Pattern.compile(" ").matcher(str);
            while (matcher.find()) {
                spaces++;
            }
            double ratio = spaces / (str.length()) * 100.0;
//            double scale = Math.pow(10,2);
//            double result = Math.round(ratio * scale) / scale;
//            System.out.println(result);
            System.out.printf("%.2f", ratio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
