package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(Integer m, int n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(Integer m, Integer n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, Integer n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(byte m, int n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(byte m, byte n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, byte n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(short m, byte n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(short m, short n, String value) {
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }
}
