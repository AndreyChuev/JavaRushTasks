package com.javarush.task.pro.task10.task1017;

/* 
Создание материков
*/

public class Earth {
    public static void main(String[] args) {
        Africa africa = new Africa(30_370_000);
        Antarctica antarctica = new Antarctica(28_444_934);
        Australia australia = new Australia( 7_692_024);
        Eurasia eurasia = new Eurasia( 55_000_000);
        NorthAmerica northAmerica = new NorthAmerica(24_709_000);
        SouthAmerica southAmerica = new SouthAmerica(17_840_000);
    }
}
