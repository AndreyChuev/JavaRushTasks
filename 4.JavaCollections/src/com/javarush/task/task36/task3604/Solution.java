package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
*/

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();

        TreeMap<Object, Object> treeMap;
        int[] ints = {12, 15, 8, 3, 45, 87, -150, 1};
        for (int anInt : ints) {
            redBlackTree.insert(anInt);
        }
    }


}
