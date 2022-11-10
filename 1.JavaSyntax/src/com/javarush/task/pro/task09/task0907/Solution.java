package com.javarush.task.pro.task09.task0907;

import java.util.Arrays;
import java.util.regex.Pattern;

/* 
Шестнадцатеричный конвертер
*/

public class Solution {
    private static final String HEX = "0123456789abcdef";

    public static void main(String[] args) {
        int decimalNumber = 1256;
        System.out.println("Десятичное число " + decimalNumber + " равно шестнадцатеричному числу " + toHex(decimalNumber));
        String hexNumber = "4e8";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно десятичному числу " + toDecimal(hexNumber));
    }

    public static String toHex(int decimalNumber) {
        String result = "";
        char[] hexArr = HEX.toCharArray();
        while (decimalNumber > 0) {
            result = hexArr[decimalNumber % 16] + result;
            decimalNumber = decimalNumber / 16;
        }
        return result;
    }

    public static int toDecimal(String hexNumber) {
        int result = 0;
        if (hexNumber == null | "".equals(hexNumber)) {
            return 0;
        } else {
            char[] hexArr = HEX.toCharArray();
            char[] hexInputArr = hexNumber.toCharArray();
            for (int i = 0; i < hexInputArr.length; i++) {
                result = 16 * result + Arrays.binarySearch(hexArr, hexInputArr[i]);
            }
        }
        return result;
    }
}
