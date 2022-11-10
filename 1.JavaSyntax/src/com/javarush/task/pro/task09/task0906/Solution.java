package com.javarush.task.pro.task09.task0906;

import java.util.Arrays;
import java.util.regex.Pattern;

/* 
Двоичный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = Integer.MAX_VALUE;
        System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toBinary(decimalNumber));
        String binaryNumber = "1111111111111111111111111111111";
        System.out.println("Двоичное число " + binaryNumber + " равно десятичному числу " + toDecimal(binaryNumber));
    }

    public static String toBinary(int decimalNumber) {
        String result = "";
        while (decimalNumber > 0) {
            result = decimalNumber % 2 + result;
            decimalNumber = decimalNumber / 2;
        }
        return result;
    }

    public static int toDecimal(String binaryNumber) {
        int dec = 0;
        if ( binaryNumber == null | "".equals(binaryNumber)) {
            return 0;
        } else {
            dec = 0;
            char[] binArr = binaryNumber.toCharArray();
            for (int i = 0, j = binArr.length - 1; i < binArr.length; i++, j--) {
                dec = dec + (binArr[j] % 2) * (int) Math.pow(2, i);
            }
        }
        return dec;
    }
}
