package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1921\test.txt
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            while (reader.ready()) {
                String line = reader.readLine();
                PEOPLE.add(new Person(parseName(line),parseDate(line)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Date parseDate(String str) {
        String strDate = "";
        Matcher matcher = Pattern.compile("\\d.+").matcher(str);
        if (matcher.find())
            strDate = matcher.group();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            System.out.println("Усё пропало, Шеф!");
            e.printStackTrace();
        }
        return date;
    }

    private static String parseName(String line) {
        String result = "";
        Matcher matcher = Pattern.compile("\\s\\d").matcher(line);
        if (matcher.find())
            result = line.substring(0,matcher.start());
        return result;
    }
}
