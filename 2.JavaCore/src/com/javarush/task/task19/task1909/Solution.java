package com.javarush.task.task19.task1909;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Замена знаков
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1909\OneFile.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1909\twoFile.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(console.readLine()));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(console.readLine()))){

            StringBuilder string = new StringBuilder();
            while (fileReader.ready())
                string.append(fileReader.readLine());

            String textFile = string.toString();

            fileWriter.write(textFile.replaceAll("\\.", "!"));

        } catch (IOException e) {
            System.out.println("Все пиздец, всё накрылось!");
            e.printStackTrace();
        }
    }
}
