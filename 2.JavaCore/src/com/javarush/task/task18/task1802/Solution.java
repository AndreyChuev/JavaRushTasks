package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        TreeSet<Integer> setByte = new TreeSet<>(Integer::compare);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream input = new FileInputStream(reader.readLine())) {
            while (input.available() > 0) {
                setByte.add(input.read());
            }
            System.out.println(setByte.first());
        }
    }
}
