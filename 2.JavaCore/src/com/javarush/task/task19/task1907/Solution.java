package com.javarush.task.task19.task1907;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Считаем слово
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1907\textFile.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader = new BufferedReader(new FileReader(consoleReader.readLine()))){

            StringBuilder string = new StringBuilder();
            while (reader.ready())
                string.append(reader.readLine()).append(" ");

            int wordCount = 0;
            Matcher matcher = Pattern.compile("\\bworld\\b").matcher(string);
            while (matcher.find())
                wordCount++;

            System.out.println(wordCount);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
