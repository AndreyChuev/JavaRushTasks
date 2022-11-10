package com.javarush.task.task12.task1207;

/* 
Int и Integer
*/

public class Solution {
    public static void main(String[] args) {
        print(10);
        print(Integer.valueOf(1000));
    }

    static void print(int i) {
        System.out.println(i);
    }

    static void print(Integer integer) {
        System.out.println(integer);
    }
}
