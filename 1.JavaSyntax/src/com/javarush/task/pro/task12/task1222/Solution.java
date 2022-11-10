package com.javarush.task.pro.task12.task1222;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Выводим в консоли разные типы
*/

public class Solution {

    public static void printAnything(ArrayList arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }

    public static void main(String[] args) {

                Integer[] i = {1,2,3,4,5};

                printArray(i);//ошибка, не компилируется!
    }
    public static void printArray(Integer[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}


