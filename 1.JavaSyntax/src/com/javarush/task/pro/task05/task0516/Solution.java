package com.javarush.task.pro.task05.task0516;

import java.util.Arrays;

/* 
Заполняем массив
*/

public class Solution {

    public static int[] array = new int[10];
    public static int valueStart = 10;
    public static int valueEnd = 13;

    public static void main(String[] args) {

        int halfArray = array.length % 2 == 0 ? array.length / 2 : (int) Math.ceil(array.length / 2.0);
        Arrays.fill(array, halfArray, array.length, valueEnd);
        Arrays.fill(array, 0, halfArray, valueStart);

        System.out.println(Arrays.toString(array));
    }
}
