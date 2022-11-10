package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static {
        labels.put(13.0, "Петя");
        labels.put(16.0, "Вася");
        labels.put(11.0, "Коля");
        labels.put(20.0, "Андрей");
        labels.put(18.0, "Андрей");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
