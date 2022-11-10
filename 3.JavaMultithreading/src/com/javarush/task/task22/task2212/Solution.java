package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        boolean result = false;
        if (telNumber != null && telNumber.matches("^\\+.+")) {
            if (controlDigitLength(telNumber,12)) {
                result = telNumber.matches("^\\+\\d+\\(\\d{3}\\)\\d+$|^\\+\\d+$");
            }
        } else if (telNumber != null && telNumber.matches("^\\d.+|^\\(.+")) {
            if (controlDigitLength(telNumber,10)) {
                result = telNumber.matches("^\\d+\\(\\d{3}\\)\\d+$|^\\(\\d{3}\\)\\d+$|^\\d+$");
            }
        }
        return result;
    }

    private static boolean controlDigitLength(String str, int maxLength) {
        Matcher matcher = Pattern.compile("\\d").matcher(str);
        int count = 0;
        while (matcher.find()) count++;
        return count == maxLength;
    }

//    private static void printCheck(String tel, boolean expect) {
//        boolean result = checkTelNumber(tel) == expect;
//        String sign = result ? "V" : "X";
//        System.out.printf("[ %20s ] check: [ %s ]\n", tel,  sign);
//    }

    public static void main(String[] args) {
//        printCheck("+380501234567", true);
//        printCheck("+38(050)1234567", true);
//        printCheck("(050)1234567", true);
//        printCheck("9636664103", true);
//        printCheck("0(501)234567", true);
//        printCheck("+38)050(1234567", false);
//        printCheck("+38(050)123-45-67",false);
//        printCheck("050ххх4567", false);
//        printCheck("050123456",false);
//        printCheck("(0)501234567",false);
    }
}
