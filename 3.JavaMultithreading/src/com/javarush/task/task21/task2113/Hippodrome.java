package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public void run() {
        for (int i = 1; i < 101; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public void print() {
        for (int i = 0; i < 10; i++) System.out.println();
        horses.forEach(Horse::print);
    }

    public Horse getWinner() {
        return horses.stream().max(Horse::compareTo).get();
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().name);
    }

    public static void main(String[] args) {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Marty",3d, 0d));
        horses.add(new Horse("Sobchack",3d,0d));
        horses.add(new Horse("Guzeeva",3d,0d));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }
}
