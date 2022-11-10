package com.javarush.task.task19.task1913;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Выводим только цифры
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream tempStream = new PrintStream(byteArrayOutputStream);

        System.setOut(tempStream);

        testString.printSomething();

        String line = "";
        Matcher matcher = Pattern.compile("\\d").matcher(byteArrayOutputStream.toString());
        while (matcher.find())
            line += matcher.group();

        System.setOut(printStream);

        System.out.println(line);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
