package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* 
Обращенные слова
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        Path file;
        List<String> lines = null;
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            file = Paths.get(consoleReader.readLine());
            lines = Files.readAllLines(file, Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("\nУсё пропало!");
            e.printStackTrace();
        }
        if (lines == null)
            throw new NullPointerException("lines = null!");

        StringBuilder builder = new StringBuilder();
        lines.forEach(s -> builder.append(s).append(" "));
        ArrayList<String> words = new ArrayList<>(Arrays.asList(builder.toString().split("\\s")));

        while (!words.isEmpty()) {
            try {
                Pair pair = searchReverseCouple(words.get(0), words);
                result.add(pair);
            } catch (NoSuchElementException e) {
            }
        }

        result.forEach(System.out::println);
    }

    private static Pair searchReverseCouple(String word, ArrayList<String> array) {
        Pair pair = new Pair();
        pair.first = word;
        array.remove(word);
        String reverseWord = new StringBuilder(word).reverse().toString();
        int index = array.indexOf(reverseWord);
        if (index != -1) pair.second = array.remove(index);
        if (pair.second == null) throw new NoSuchElementException();
        return pair;
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
