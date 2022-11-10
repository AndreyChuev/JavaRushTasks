package com.javarush.task.pro.task05.task0508;

import java.util.Scanner;

/* 
Удаляем одинаковые строки
*/

public class Solution {
    public static String[] strings;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//       strings = new String[]{"Hello", "Hello", "World", "Java", "Tasks", "World"};
        strings = new String[6];

        for (int i = 0; i < 6; i++) {
            strings[i] = scan.nextLine();
        }

        for (int i = 0; i < strings.length; i++) {
            String arr = strings[i];
            if (arr != null) {
                for (int j = 0; j < strings.length; j++) {
                    if (arr.equalsIgnoreCase(strings[j]) && i != j)
                        strings[j] = strings[i] = null;
                }
            }
        }

        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + ", ");
        }
    }
}
