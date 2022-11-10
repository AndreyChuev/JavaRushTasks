package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
             InputStream input = new FileInputStream(scanner.nextLine());
             InputStreamReader reader = new InputStreamReader(input);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            List<Integer> list = bufferedReader.lines()
                    .map(Integer::parseInt)
                    .filter(x -> x % 2 == 0)
                    .sorted()
                    .collect(Collectors.toList());

            list.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
