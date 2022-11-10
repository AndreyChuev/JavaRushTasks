package com.javarush.task.task17.task1714;

/* 
Comparable
*/

import java.util.ArrayList;
import java.util.List;

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

//    @Override
//    public String toString() {
//        return "name=" + name + " " + "distance=" + distance + " " + "quality=" + quality;
//    }

    public static void main(String[] args) {
//        List<Beach> beachList = new ArrayList<>();
//        beachList.add(new Beach("Маями",100.25f,5 )); 1
//        beachList.add(new Beach("Куба",100.10f,5 ));
//        beachList.add(new Beach("Геленжик",95,5));
//        beachList.add(new Beach("Сочи",100,5));
//        beachList.add(new Beach("Турция",100,5));
//        beachList.add(new Beach("Тайланд",50,5));
//
//        for (Beach beach : beachList) {
//            for (Beach beach1 : beachList) {
//                int res = beach.compareTo(beach1);
//                System.out.println("-----------------------------------------------------------------------");
//                System.out.printf("%s лучше чем %s: результат %d\n",beach.getName(), beach1.getName(), res);
//                System.out.println("-----------------------------------------------------------------------");
//            }
//        }
    }

    @Override
    public synchronized int compareTo(Beach o) {
        int result = 0;
        if (distance < o.distance) {
            result += 1;
        } else if (distance > o.distance){
            result += -1;
        }

        if (quality > o.quality) {
            result += 1;
        } else if (quality < o.quality){
            result += -1;
        }
        return result;
    }
}
