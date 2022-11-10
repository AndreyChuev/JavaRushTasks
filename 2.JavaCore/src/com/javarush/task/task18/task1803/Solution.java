package com.javarush.task.task18.task1803;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        HashMap<Integer, Integer> bytes = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream input = new FileInputStream(reader.readLine())) {
            while (input.available() > 0) {
                Integer readByte = input.read();
                if (bytes.containsKey(readByte)) {
                    int count = bytes.get(readByte) + 1;
                    bytes.put(readByte,count);
                } else {
                    bytes.put(readByte,1);
                }
            }

            int max = bytes.values().stream()
                    .max(Integer::compare).get();

            bytes.keySet().stream()
                    .filter(integer -> bytes.get(integer).equals(max))
                    .forEach(x -> System.out.print(x + " "));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
