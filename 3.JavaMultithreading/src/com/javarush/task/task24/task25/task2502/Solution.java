package com.javarush.task.task24.task25.task2502;


import java.util.*;

/* 
Машину на СТО не повезем!
*/

public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<>();
            String[] strings = loadWheelNamesFromDB();
            if (strings.length != 4)
                throw new RuntimeException();
            for (String s : strings) {
                wheels.add(Wheel.valueOf(s));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
