package com.javarush.task.task26.task2601;

import java.util.*;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static Integer[] sort(Integer[] array) {
        Integer median = median(array);
        Arrays.sort(array, new MedianComparator(median));
        return array;
    }

    private static Integer median(Integer[] array) {
        Arrays.sort(array);
        if (array.length % 2 == 0)
            return (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        else
            return array[array.length / 2];
    }


    private static class MedianComparator implements Comparator<Integer> {
        private final Integer median;

        public MedianComparator(Integer median) {
            this.median = median;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(Math.abs(median - o1), Math.abs(median - o2));
        }
    }
}
