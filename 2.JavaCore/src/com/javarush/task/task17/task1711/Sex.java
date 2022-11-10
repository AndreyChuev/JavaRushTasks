package com.javarush.task.task17.task1711;

public enum Sex {
    MALE,
    FEMALE;

    @Override
    public String toString() {
        return this.equals(MALE) ? "м" : "ж";
    }

    public static Sex parseSex(String s) {
        return s.equals("м") ? MALE : FEMALE;
    }
}
