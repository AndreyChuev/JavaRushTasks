package com.javarush.task.pro.task09.task0909;

/* 
Экранирование символов
*/

public class Solution {
    public static void main(String[] args) {
        String str1 = "It's a Windows path: \"C:\\Program Files\\Java\\jdk-13.0.0\\bin\"";
        String str2 = "\nIt's a Java string: \\\"C:\\\\Program Files\\\\Java\\\\jdk-13.0.0\\\\bin\\\"";
        System.out.println(str1 + str2);
    }
}
