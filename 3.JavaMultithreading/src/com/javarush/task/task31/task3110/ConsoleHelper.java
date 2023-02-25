package com.javarush.task.task31.task3110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    private static final BufferedReader consoleReader;

    static {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                consoleReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return consoleReader.readLine();
    }

    public static int readInt() throws IOException {
        return Integer.parseInt(readString());
    }

}
