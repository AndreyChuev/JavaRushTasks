package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
        while (!isValuePresent) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Got: " + value);
        isValuePresent = false;
        return value;
    }

    public synchronized void put(int value) {
        this.value = value;
        isValuePresent = true;
        notifyAll();
        System.out.println("Put: " + value);
    }
}
