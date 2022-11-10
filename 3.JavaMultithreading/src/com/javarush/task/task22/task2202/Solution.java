package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/

import java.util.TooManyListenersException;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
//        System.out.println(getPartOfString("Амиго и Диего лучшие друзья!"));
//        System.out.println(getPartOfString("Амиго и Диего лучшие!"));
    }

    public static String getPartOfString(String string) {
        if (string == null) throw new TooShortStringException();
        int count = 0;
        for (char c : string.toCharArray()) {
            if (c == ' ') count++;
            if (count == 4) break;
        }
        if (count < 4) throw new TooShortStringException();
        String sub = string.substring(string.indexOf(' ') + 1);
        int end = sub.length();
        count = 0;
        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) == ' ') {
                count++;
                if (count == 4) {
                    end = i;
                }
            }
        }
        return sub.substring(0,end);
    }

    public static class TooShortStringException extends RuntimeException {

        public TooShortStringException() {
        }

        public TooShortStringException(String message) {
            super(message);
        }

        public TooShortStringException(String message, Throwable cause) {
            super(message, cause);
        }

        public TooShortStringException(Throwable cause) {
            super(cause);
        }
    }
}
