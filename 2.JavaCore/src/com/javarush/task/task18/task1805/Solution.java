package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        TreeSet<Integer> setByte = new TreeSet<>(Integer::compare);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream input = new FileInputStream(reader.readLine())) {
            while (input.available() > 0) {
                setByte.add(input.read());
            }
            setByte.forEach(i -> System.out.print(i + " "));
        }
    }
}
