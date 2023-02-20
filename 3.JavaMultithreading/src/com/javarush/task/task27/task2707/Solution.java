package com.javarush.task.task27.task2707;

/* 
Определяем порядок захвата монитора
*/


import java.util.concurrent.atomic.AtomicBoolean;

public class Solution {
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        new Thread(() -> {
            synchronized (o1) {
                sleep(500);
                synchronized (o2) {
                    sleep(500);
                }
            }
        }).start();

        Thread checkThread = new Thread(() -> {
            solution.someMethodWithSynchronizedBlocks(o1, o2);
        });
        checkThread.start();
        sleep(2000);
        return checkThread.getState() != Thread.State.BLOCKED;
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
