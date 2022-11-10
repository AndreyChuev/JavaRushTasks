package com.javarush.task.pro.task16.task1607;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

/* 
Освоение нового API
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(nowExample());
        System.out.println(ofExample());
        System.out.println(ofYearDayExample());
        System.out.println(ofEpochDayExample());
    }

    static LocalDate nowExample() {
        return LocalDate.now();
    }

    static LocalDate ofExample() {
        return LocalDate.of(2020, Month.SEPTEMBER, 12);
    }

    static LocalDate ofYearDayExample() {
        return LocalDate.ofYearDay(2020, ofExample().getDayOfYear());
    }

    static LocalDate ofEpochDayExample() {
        return LocalDate.ofEpochDay(ofExample().toEpochDay());
    }
}
