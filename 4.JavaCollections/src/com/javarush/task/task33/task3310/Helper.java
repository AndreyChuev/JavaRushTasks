package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateRandomString() {
        return new BigInteger((8), SECURE_RANDOM).toString(36);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
