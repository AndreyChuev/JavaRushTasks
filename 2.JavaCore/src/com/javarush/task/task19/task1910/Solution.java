package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Пунктуация
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1910\oneFile.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1910\twoFile.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new FileReader(console.readLine()));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(console.readLine()))){

            StringBuilder stringBuilder = new StringBuilder();
            while (fileReader.ready())
                stringBuilder.append(fileReader.readLine());

            String result = stringBuilder.toString().replaceAll("\\p{Punct}", "");

            fileWriter.write(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
