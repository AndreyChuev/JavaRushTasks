package com.javarush.task.task30.task3008;

import java.io.UnsupportedEncodingException;

public class StringUtils {

    public static String encodeToWindows1251(String text) {
        try {
            return new String(text.getBytes("UTF-8"), "windows-1251");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
