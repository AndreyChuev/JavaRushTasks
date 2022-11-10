package com.javarush.task.task20.task2025;

import java.util.*;

/* 
Алгоритмы-числа
{1,2,3,4,5,6,7,8,9,153,370,371,407,1634,8208,9474,54748,92727,93084,548834,1741725,4210818,9800817,9926315,24678050,
24678051,88593477,146511208,472335975,534494836,912985153,4679307774,32164049650,32164049651,40028394225,42678290603,
44708635679,49388550606,82693916578,94204591914,28116440335967,4338281769391370,4338281769391371,21897142587612075,
35641594208964132,35875699062250035,1517841543307505039,3289582984443187032,4498128791164624869,4929273885928088826}
*/

public class Solution {

    public static long[] getNumbers(long N) {
        List<Long> list = ArmstrongNumbersMultiSetLongOpt.generate(String.valueOf(N).length());
        List<Long> resultList = new ArrayList<>();
        for (Long aLong : list) {
            if (aLong < N) resultList.add(aLong);
        }

        long[] arms = new long[resultList.size()];
        int i = 0;
        for (Long aLong : resultList) {
            arms[i] = aLong;
            i++;
        }
        return arms;
    }


    public static void printLiteral(long[] numbers) {
        StringJoiner joiner = new StringJoiner("L,");
        for (long number : numbers) {
            joiner.add(String.valueOf(number));
        }
        System.out.println("{" + joiner.toString() + "}");
    }


    public static void main(String[] args) {
        long a = System.currentTimeMillis();
//        System.out.println(Arrays.toString(getNumbers(1000)));
//        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        printLiteral(getNumbers(Long.MAX_VALUE));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }

    private static class ArmstrongNumbersMultiSetLongOpt {
        private static int N;
        private static int[] digitsMultiSet;
        private static int[] testMultiSet;

        private static List<Long> results;
        private static long maxPow;
        private static long minPow;

        private static long[][] pows;

        private static void genPows(int N) {
            if (N > 20) throw new IllegalArgumentException();
            pows = new long[10][N + 1];
            for (int i = 0; i < pows.length; i++) {
                long p = 1;
                for (int j = 0; j < pows[i].length; j++) {
                    pows[i][j] = p;
                    p *= i;
                }
            }
        }

        private static boolean check(long pow) {
            if (pow >= maxPow) return false;
            if (pow < minPow) return false;

            for (int i = 0; i < 10; i++) {
                testMultiSet[i] = 0;
            }

            while (pow > 0) {
                int i = (int) (pow % 10);
                testMultiSet[i]++;
                if (testMultiSet[i] > digitsMultiSet[i]) return false;
                pow = pow / 10;
            }

            for (int i = 0; i < 10; i++) {
                if (testMultiSet[i] != digitsMultiSet[i]) return false;
            }

            return true;
        }

        private static void search(int digit, int unused, long pow) {
            if (pow >= maxPow) return;

            if (digit == -1) {
                if (check(pow)) results.add(pow);
                return;
            }

            if (digit == 0) {
                digitsMultiSet[digit] = unused;
                search(digit - 1, 0, pow + unused * pows[digit][N]);
            } else {
                // Check if we can generate more than minimum
                if (pow + unused * pows[digit][N] < minPow) return;

                long p = pow;
                for (int i = 0; i <= unused; i++) {
                    digitsMultiSet[digit] = i;
                    search(digit - 1, unused - i, p);
                    if (i != unused) {
                        p += pows[digit][N];
                        // Check maximum and break the loop - doesn't help
                        // if (p >= maxPow) break;
                    }
                }
            }
        }

        public static List<Long> generate(int maxN) {
            genPows(maxN);
            results = new ArrayList<>();
            digitsMultiSet = new int[10];
            testMultiSet = new int[10];

            for (N = 1; N <= maxN; N++) {
                minPow = (long) Math.pow(10, N - 1);
                maxPow = (long) Math.pow(10, N);

                search(9, N, 0);
            }

            Collections.sort(results);

            return results;
        }
    }

}
