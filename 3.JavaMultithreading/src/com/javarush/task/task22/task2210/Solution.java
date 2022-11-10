package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));
    }

    public static String[] getTokens(String query, String delimiter) {
        List<String> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(query,delimiter);
        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }
        return result.toArray(new String[0]);
    }
}
