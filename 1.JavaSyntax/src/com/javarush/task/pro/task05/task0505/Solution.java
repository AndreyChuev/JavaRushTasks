package com.javarush.task.pro.task05.task0505;

import java.sql.SQLOutput;
import java.util.Scanner;

/* 
Reverse
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int[] array = new int[N];

        if (N > 0) {
            for (int i = 0; i < N; i++) {
                array[i] = scan.nextInt();
            }
        }

        if (N % 2 != 0) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        } else {
            for (int i = array.length - 1; i >= 0 ; i--) {
                System.out.println(array[i]);
            }
        }
    }
}
