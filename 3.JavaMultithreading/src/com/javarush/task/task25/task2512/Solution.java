package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        LinkedList<Throwable> throwables = new LinkedList<>();
        do {
            throwables.addFirst(e);
        } while ((e = e.getCause()) != null);
        throwables.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new IllegalArgumentException("ABC", new RuntimeException("DEF", new ArithmeticException("GHI")));
        });
        thread.setUncaughtExceptionHandler(new Solution());
        thread.start();
    }
}
