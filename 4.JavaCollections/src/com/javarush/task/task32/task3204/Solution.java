package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Генератор паролей
^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        Pattern pattern = Pattern.compile("^.*(?=.{8})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$");
        String dest = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder builder = new StringBuilder();

        Random random = new Random();
        while (!pattern.matcher(builder).matches()) {
            builder.append(dest.charAt(random.nextInt(dest.length())));
            if (builder.length() > 8) builder.delete(0, 5);
        }

        try {
            result.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
