package com.javarush.task.pro.task17.task1708;

/* 
Минимальное и максимальное
*/

public class MinMaxUtil {

    public static int max (int x, int y) {
        return Math.max(x,y);
    }

    public static int max (int x, int y, int z) {
        return Math.max(x,Math.max(y,z));
    }

    public static int max (int x, int y, int z, int k) {
        return Math.max(Math.max(x,y),Math.max(z,k));
    }

    public static int max (int x, int y, int z, int k, int h) {
        return Math.max(Math.max(Math.max(x,y),Math.max(z,k)),h);
    }

    public static int min (int x, int y) {
        return Math.min(x,y);
    }

    public static int min (int x, int y, int z) {
        return Math.min(x,Math.min(y,z));
    }

    public static int min (int x, int y, int z, int k) {
        return Math.min(Math.min(x,y),Math.min(z,k));
    }

    public static int min (int x, int y, int z, int k, int h) {
        return Math.min(Math.min(Math.min(x,y),Math.min(z,k)),h);
    }

}
