package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> characters = new TreeSet<>();

        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
        }

        for (char c : getChars(builder)) {
            characters.add(c);
        }

        int i = 0;
        for (Character character : characters) {
            if (i == 5) break;
            System.out.print(character);
            i++;
        }
    }

    private static char[] getChars(StringBuilder builder) {
        return builder.toString().replaceAll("\\W", "").toLowerCase().toCharArray();
    }
}

