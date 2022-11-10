package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String filename ="";
            while (!(filename = reader.readLine()).equals("exit")) {
                new ReadThread(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (Map.Entry<String, Integer> stringIntegerEntry : resultMap.entrySet()) {
//            String file = stringIntegerEntry.getKey();
//            String value = stringIntegerEntry.getValue().toString();
//            System.out.println(file + ": " + value);
//        }
    }

    public static class ReadThread extends Thread {
        private String fileName;

        public ReadThread(String fileName) {
            super();
            this.fileName = fileName;
            start();
        }

        @Override
        public void run() {
            resultMap.put(fileName, getMaxByte());
        }

        private Integer getMaxByte() {
            Map.Entry<Integer, Integer> max = null;
            try (FileInputStream input = new FileInputStream(fileName)) {
                HashMap<Integer, Integer> map = new HashMap<>();
                while (input.available() != 0) {
                    Integer b = input.read();
                    if (map.containsKey(b)) {
                        Integer v = map.get(b);
                        map.put(b,++v);
                    } else {
                        map.put(b,1);
                    }
                }
                for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
                    if (max == null) max = integerIntegerEntry;
                    max = max.getValue() > integerIntegerEntry.getValue() ? max : integerIntegerEntry;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return max.getKey();
        }
    }
}
