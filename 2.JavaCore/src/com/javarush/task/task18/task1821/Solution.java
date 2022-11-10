package com.javarush.task.task18.task1821;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader(args[0])){
            TreeMap<Character, Integer> map = new TreeMap<>();
            while (reader.ready()) {
                Character ch = (char) reader.read();
                if (map.containsKey(ch)) {
                    int v = map.get(ch);
                    map.put(ch,++v);
                } else {
                    map.put(ch,1);
                }
            }
            for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
                String key = characterIntegerEntry.getKey().toString();
                String value = characterIntegerEntry.getValue().toString();
                System.out.printf("%s %s\n", key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
