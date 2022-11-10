package com.javarush.task.pro.task12.task1220;

import java.util.ArrayList;

/* 
Охотники за привидением: в погоне за типами
*/

public class Solution {

    public static void main(String[] args) {
        ArrayList numbers = new ArrayList();

        for (int i = 0; i < 10; i++) {
            numbers.add(i * 10);
            System.out.println(numbers.get(i));
        }
    }

    public static void checkElementsType(ArrayList<Object> elements) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) instanceof String) {
                printString();
            } else if (elements.get(i) instanceof Integer) {
                printInteger();
            } else if (elements.get(i) instanceof Integer[]) {
                printIntegerArray();
            } else {
                printUnknown();
            }
        }
    }

    public static void printString() {
        System.out.println("Строка");
    }

    public static void printInteger() {
        System.out.println("Целое число");
    }

    public static void printIntegerArray() {
        System.out.println("Массив целых чисел");
    }

    public static void printUnknown() {
        System.out.println("Другой тип данных");
    }
}
