package com.javarush.task.pro.task13.task1309;

import java.util.HashMap;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        grades.put("Иванов Иван", 3.3);
        grades.put("Казанцева Екатерина", 4.3);
        grades.put("Калмыков Игорь", 4.5);
        grades.put("Акберли Александр", 2.3);
        grades.put("Малышко Роман", 3.3);
    }
}
