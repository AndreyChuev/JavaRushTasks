package com.javarush.task.task19.task1923;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Слова с цифрами
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1923\input.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1923\output.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new FileReader(args[0]));
             BufferedWriter output = new BufferedWriter(new FileWriter(args[1]))){
            StringBuilder builder = new StringBuilder();
            StringBuilder result = new StringBuilder();
            while (input.ready()) {
                builder.append(input.readLine()).append(" ");
            }
            String[] wordsArray = builder.toString().split("\\s");
            for (String s : wordsArray) {
                Matcher matcher = Pattern.compile("\\d+").matcher(s);
                if (matcher.find()) {
                    result.append(s).append(" ");
                }
            }
            output.write(result.toString());
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
    }
}
