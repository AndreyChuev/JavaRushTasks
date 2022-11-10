package com.javarush.task.pro.task17.task1703;

import java.util.ArrayList;
import java.util.Collections;

/* 
Космическая одиссея ч.1
*/

public class Solution {
    public static ArrayList<Astronaut> astronauts = new ArrayList<>();

    public static void main(String[] args) {
        createCrew(new Human(),new Human(),new Dog(),new Cat());
        printCrewInfo();
    }

    public static void createCrew(Astronaut... args){
        Collections.addAll(astronauts, args);
    }

    public static void printCrewInfo() {
        System.out.println("На борт погружены члены экипажа: ");
        for (Astronaut astronaut : astronauts) {
            System.out.println(astronaut.getInfo());
        }
    }
}
