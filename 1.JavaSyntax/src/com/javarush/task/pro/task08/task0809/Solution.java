package com.javarush.task.pro.task08.task0809;

/* 
Counter

*/
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        printNumbers();
    }

    public static void printNumbers() throws InterruptedException {
//        int sleepCount = 0;
        for (int i = 1; i < 11 ; i++) {
            System.out.println(i);
            if (i < 10) {
//                System.out.println("Sleep: " + (++sleepCount));
                Thread.sleep(1, 300_000);
            }

        }
    }
}
