package com.javarush.task.pro.task05.task0511;

import java.util.Scanner;

/* 
Создаем двумерный массив
*/

public class Solution {
    public static int[][] multiArray;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        if (N > 0) {
            multiArray = new int[N][];
        }

        for (int i = 0; i < multiArray.length; i++) {
            int x = scan.nextInt();
            if (x > 0) {
                multiArray[i] = new int[x];
            }
        }
    }
}
