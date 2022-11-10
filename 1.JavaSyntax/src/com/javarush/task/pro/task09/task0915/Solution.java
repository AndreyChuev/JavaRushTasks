package com.javarush.task.pro.task09.task0915;

import java.util.Arrays;
import java.util.StringTokenizer;

/* 
StringTokenizer
*/

public class Solution {
    public static void main(String[] args) {
        String packagePath = "java.util.stream";
        String[] tokens = getTokens(packagePath, "\\.");
        System.out.println(Arrays.toString(tokens));
    }

    public static String[] getTokens(String query, String delimiter) {
        StringTokenizer strtoken = new StringTokenizer(query,delimiter);
        String[] result = new String[6];
        for (int i = 0; strtoken.hasMoreTokens(); i++) {
            result[i] = strtoken.nextToken();
        }
        return result;
    }
}
