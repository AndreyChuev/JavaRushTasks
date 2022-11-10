package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream printStream = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        testString.printSomething();

        String task = byteArrayOutputStream.toString();

        String[] separator = task.split(" ");
        int firstNumber = Integer.parseInt(separator[0]);
        String operate = separator[1];
        int secondNumber = Integer.parseInt(separator[2]);
        String equal = separator[3];
        int result;

        task = separator[0] + " " + separator[1] + " " + separator[2] + " " + separator[3] + " ";

        switch (operate) {
            case "+":
                result = firstNumber + secondNumber;
                task += result;
                break;
            case "-":
                result = firstNumber - secondNumber;
                task += result;
                break;
            case "*":
                result = firstNumber * secondNumber;
                task += result;
                break;
        }

        System.setOut(printStream);
        System.out.println(task);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }

    public enum Operate {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLY("*");

        private final String operate;

        Operate(String operate) {
            this.operate = operate;
        }

        @Override
        public String toString() {
            return operate;
        }
    }
}

