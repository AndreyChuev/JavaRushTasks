package com.javarush.task.task19.task1915;

import java.io.*;

/* 
Дублируем текст
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1915\testFile.txt
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStream = System.out;
        String file = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        testString.printSomething();

        System.setOut(printStream);

        try (FileOutputStream outputStream = new FileOutputStream(file)){
            System.out.println(byteArrayOutputStream.toString());
            outputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

