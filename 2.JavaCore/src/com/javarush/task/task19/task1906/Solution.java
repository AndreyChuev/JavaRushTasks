package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Четные символы
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1906\OneFile.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1906\TwoFile.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             FileReader fileReader = new FileReader(console.readLine());
             FileWriter fileWriter = new FileWriter(console.readLine())) {

            ArrayList<Character> characters = new ArrayList<>();
            while (fileReader.ready())
                characters.add((char) fileReader.read());

            for (int i = 0, n = 1; i < characters.size(); i++, n++) {
                if (n % 2 == 0)
                    fileWriter.write(characters.get(i));
            }

        } catch (IOException e) {
            System.out.println("Все пиздец, всё накрылось!");
            e.printStackTrace();
        }
    }
}
