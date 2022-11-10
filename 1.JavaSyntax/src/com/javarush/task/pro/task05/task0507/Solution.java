package com.javarush.task.pro.task05.task0507;

import java.util.Scanner;

/* 
Максимальное из N чисел
*/

public class Solution {
    public static int[] array;

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        array = new int[N];

        for (int i = 0; i < array.length; i++) {
            array[i] = scan.nextInt();
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i])
                max = array[i];
        }
        System.out.println(max);
    }
}
