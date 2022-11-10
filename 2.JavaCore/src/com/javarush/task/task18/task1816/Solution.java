package com.javarush.task.task18.task1816;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader(args[0])){
            int count = 0;
            Pattern pattern = Pattern.compile("[A-Za-z]");
            while (reader.ready()) {
                Matcher matcher = pattern.matcher(String.valueOf((char) reader.read()));
                if (matcher.matches())
                    count++;
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
