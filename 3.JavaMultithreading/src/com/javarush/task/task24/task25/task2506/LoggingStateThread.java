package com.javarush.task.task24.task25.task2506;

public class LoggingStateThread extends Thread {

    private Thread target;
    public LoggingStateThread(Thread target) {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        State state = target.getState();
        System.out.println(state);
        while (state != State.TERMINATED) {
            State current = target.getState();
            if (current != state) {
                System.out.println(current);
                state = current;
            }
        }
    }
}
