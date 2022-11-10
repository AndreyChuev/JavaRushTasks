package com.javarush.task.task21.task2113;

import java.util.Random;

public class Horse implements Comparable<Horse> {
    String name;
    double speed;
    double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void move() {
        distance += (speed * Math.random());
    }

    public void print() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (int) Math.floor(distance); i++) {
            builder.append(".");
        }
        builder.append(name);
        System.out.println(builder);
    }

    @Override
    public int compareTo(Horse o) {
        return (int) (distance - o.distance);
    }
}
