package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Выделяем числа
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1907\textFile.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1908\result.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(console.readLine()));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(console.readLine()))){

            StringBuilder string = new StringBuilder();
            while (fileReader.ready())
                string.append(fileReader.readLine()).append(" ");

            StringBuilder integer = new StringBuilder();
            Matcher matcher = Pattern.compile("\\b\\d+\\b").matcher(string);
            while (matcher.find())
                integer.append(matcher.group()).append(" ");

            fileWriter.write(integer.toString());

        } catch (IOException e) {
            System.out.println("Все пиздец, всё накрылось!");
            e.printStackTrace();
        }
    }
}
