package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Длинные слова
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1925\input.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1925\otput.txt
*/

public class Solution {
    public static void main(String[] args) {
         try (BufferedReader input = new BufferedReader(new FileReader(args[0]));
             BufferedWriter output = new BufferedWriter(new FileWriter(args[1]))){
             StringBuilder builder = new StringBuilder();
             while (input.ready()) {
                 builder.append(input.readLine()).append(" ");
             }
             StringJoiner joiner = new StringJoiner(",");
             String[] words = builder.toString().split("\\p{Space}");
             for (String word : words) {
                 if (word.length() > 6)
                     joiner.add(word);
             }
             output.write(joiner.toString());
         } catch (IOException e) {
             System.out.println("\nУсё пропало!");
             e.printStackTrace();
         }
    }
}
