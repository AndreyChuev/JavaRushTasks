package com.javarush.task.pro.task09.task0908;

import java.util.regex.Pattern;

/* 
Двоично-шестнадцатеричный конвертер
*/

public class Solution {
    private static final String HEX = "0123456789abcdef";
    private static final String[] BIN = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};

    public static void main(String[] args) {
        String binaryNumber = "100111010000";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "9d0";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        String result = ""; // Переменная результата
        //Проверка входящего значения
        if (binaryNumber == null || binaryNumber.equals("")) return "";
        if (!binControl(binaryNumber)) return "";

        //Проверка и выравнивание длинны строки
        while (binaryNumber.length() % 4 != 0) {
            binaryNumber = "0" + binaryNumber;
        }

        //Перевод в HEX
        for (int i = 0; i < binaryNumber.length() / 4; i++) {
            int dec = 0; // переменная для записи суммы значений двоичного блока
            int posStart = 4 * i;
            int posEnd = posStart + 4;
            for (int j = posStart, x = 3; j < posEnd; j++, x--) {
                dec = dec + Character.getNumericValue(binaryNumber.charAt(j)) * (int) Math.pow(2, x);
            }
            result = result + HEX.charAt(dec);
        }
        return result;
    }

    public static String toBinary(String hexNumber) {
        String result = "";
        //Проверка входящего значения
        if (hexNumber == null || hexNumber.equals("")) return result;
        if (!hexControl(hexNumber)) return result;
        //Перевод в двоичную
        for (int i = 0; i < hexNumber.length(); i++) {
            result = result + BIN[Character.getNumericValue(hexNumber.charAt(i))];
        }
        return result;
    }

    private static boolean binControl(String binaryNumber) {
        boolean result = false;
        for (int i = 0; i < binaryNumber.length(); i++) {
            if (binaryNumber.charAt(i) == '1' | binaryNumber.charAt(i) == '0') {
                result = true;
            } else {
                return result = false;
            }
        }
        return result;
    }

    private static boolean hexControl(String hexNumber) {
        boolean result = false;
        for (int i = 0; i < hexNumber.length(); i++) {
            for (int j = 0; j < HEX.length(); j++) {
                if (hexNumber.charAt(i) == HEX.charAt(j)) {
                    result = true;
                    break;
                } else if ((hexNumber.charAt(i) != HEX.charAt(j)) & (j == HEX.length() - 1)) {
                    return result = false;
                }
            }
        }
        return result;
    }
}
