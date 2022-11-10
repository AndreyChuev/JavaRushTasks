package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new ThreadOne());
        threads.add(new ThreadTwo());
        threads.add(new ThreadThree());
        threads.add(new ThreadFour());
        threads.add(new ThreadFive());
    }

    public static void main(String[] args) throws InterruptedException {
//        threads.forEach(Thread::start);
/*        ThreadFour threadFour = new ThreadFour();
        threadFour.start();
        Thread.sleep(1000);
        threadFour.showWarning();*/
    }
}

class ThreadOne extends Thread {
    @Override
    public void run() {
        while (true);
    }
}

class ThreadTwo extends Thread  {
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
    }
}

class ThreadThree extends Thread  {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Ура");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
        }
    }
}

class ThreadFour extends Thread implements Message {

    @Override
    public void run() {
        while (!isInterrupted()) {

        }
    }

    @Override
    public void showWarning() {
        this.interrupt();
    }
}

class ThreadFive extends Thread  {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            } else if (scanner.nextLine().equals("N")) {
                System.out.println(sum);
                break;
            }
        }
    }
}