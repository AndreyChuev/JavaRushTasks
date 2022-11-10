package com.javarush.task.task14.task1419;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Integer i = null;
            Integer d = 2 + i;
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            int[] arr = new int[1];
            arr[1] = 2;
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            InputStream inputStream = new FileInputStream("C/zeroor");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            String s = "Google";
            char c = s.charAt(19);
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Integer i = Integer.parseInt("1213h");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Stack<Integer> stack = new Stack<>();
            stack.pop();
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            List<String> srt = new ArrayList<>();
            srt.get(1);
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            List<Object> objects = new ArrayList<>();
            objects.add("String");
            objects.add(1);
            for (Object object : objects) {
                String s = (String) object;
            }
        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Path path = Paths.get("C:\\Users\\Андрей\\Desktop");
            OutputStream outputStream = Files.newOutputStream(path);
            outputStream.write(121);
        } catch (Exception e) {
            exceptions.add(e);
        }

    }
}
