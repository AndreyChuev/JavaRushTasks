package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    static {
        String first = "", second = "";
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))){
            first = consoleReader.readLine();
            second = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(first))) {
            while (reader.ready()) {
                allLines.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(second))) {
            while (reader.ready()) {
                forRemoveLines.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        try {
            solution.joinData();
        } catch (CorruptedDataException e) {
            System.out.println("Операция не выполнена!");
            e.printStackTrace();
        }

//        System.out.println("List - allLines:");
//        allLines.forEach(System.out::println);
//
//        System.out.println("List - forRemoveLines:");
//        forRemoveLines.forEach(System.out::println);
    }

    public void joinData() throws CorruptedDataException {
        int stringMatches = 0;
        for (int i = 0; i < forRemoveLines.size(); i++) {
            if (allLines.contains(forRemoveLines.get(i))) {
                stringMatches++;
                allLines.remove(forRemoveLines.get(i));
            }
        }
        if (!(forRemoveLines.size() == stringMatches)) {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
