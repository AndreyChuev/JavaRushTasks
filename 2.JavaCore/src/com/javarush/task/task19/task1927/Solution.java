package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

/* 
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();
    public static String advertisementText = "JavaRush - курсы Java онлайн";

    public static void main(String[] args) {
        PrintStream printStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream advertisement = new PrintStream(outputStream);
        System.setOut(advertisement);

        testString.printSomething();

        System.setOut(printStream);
        String[] lines = outputStream.toString().split("\n");
        for (int i = 0, p = 1; i < lines.length; i++, p++) {
            if (p % 2 == 0) {
                System.out.println(lines[i]);
                System.out.println(advertisementText);
            } else {
                System.out.println(lines[i]);
            }
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
