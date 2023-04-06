package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BinaryOperator;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        RomanDigit[] romanDigits = RomanDigit.toRomanDigitArray(s);
        int result = 0;
        for (int i = 0; i < romanDigits.length; i++) {
            int currentVal = romanDigits[i].val;
            try {
                if (romanDigits[i].ordinal() < romanDigits[i + 1].ordinal()) {
                    result -= currentVal;
                } else {
                    result += currentVal;
                }
            } catch (IndexOutOfBoundsException ignore) {
                result += currentVal;
            }
        }
        return result;
    }

    private enum RomanDigit {
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

        RomanDigit(int val) {
            this.val = val;
        }

        final int val;

        static RomanDigit[] toRomanDigitArray(String line) {
            String[] parts = line.split("\\B");
            RomanDigit[] romanDigits = new RomanDigit[parts.length];
            for (int i = 0; i < parts.length; i++) {
                romanDigits[i] = valueOf(parts[i]);
            }
            return romanDigits;
        }
    }
}
