package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {

    static {
        readKeyFromConsoleAndInitPlanet();
    }

    public static void main(String[] args) {

    }

    public static Planet thePlanet;


    public static void readKeyFromConsoleAndInitPlanet() {
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case Planet.EARTH:
                thePlanet = Earth.getInstance();
                break;
            case Planet.MOON:
                thePlanet = Moon.getInstance();
                break;
            case Planet.SUN:
                thePlanet = Sun.getInstance();
                break;
            default:
                thePlanet = null;
        }
    }
}
