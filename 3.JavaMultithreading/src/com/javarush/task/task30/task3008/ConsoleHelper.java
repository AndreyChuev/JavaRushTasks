package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ConsoleHelper {

    private static final String STRING_ENTRY_ERROR = "Произошла ошибка при попытке ввода текста. Попробуйте еще раз.";
    private static final String INTEGER_ENTRY_ERROR = "Произошла ошибка при попытке ввода числа. Попробуйте еще раз.";
    private static BufferedReader reader;


    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void writeMessage(String message) {
        System.out.println(encode(message));
    }

    public static String readString() {
        try {
            return encode(reader.readLine());
        } catch (IOException e) {
            System.out.println(STRING_ENTRY_ERROR);
            return readString();
        }
    }

    public static int readInt() {
        try {
            return Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            System.out.println(INTEGER_ENTRY_ERROR);
            return readInt();
        }
    }

    private static String encode(String text) {
        try {
            return new String(text.getBytes(), "windows-1251");
        } catch (UnsupportedEncodingException ignore) {
        }
        return text;
    }
}
