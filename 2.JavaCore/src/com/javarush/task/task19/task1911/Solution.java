package com.javarush.task.task19.task1911;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Ридер обертка
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream tempStream = new PrintStream(byteArrayOutputStream);

        System.setOut(tempStream);

        testString.printSomething();

        String line = byteArrayOutputStream.toString().toUpperCase();

        System.setOut(printStream);

        System.out.println(line);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
