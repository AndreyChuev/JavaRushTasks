package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        testStrategy(new OurHashMapStorageStrategy(), 10000);
    }

    public static void testStrategy(StorageStrategy strategy, long elementNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> genStrings = Stream.generate(Helper::generateRandomString)
                .limit(elementNumber)
                .collect(Collectors.toSet());

        Shortener shortener = new Shortener(strategy);

        Date start = new Date();
        Set<Long> ids = getIds(shortener, genStrings);
        Date finish = new Date();
        Helper.printMessage(String.valueOf(finish.getTime() - start.getTime()));

        Date start1 = new Date();
        Set<String> strings = getStrings(shortener, ids);
        Date finish1 = new Date();
        Helper.printMessage(String.valueOf(finish1.getTime() - start1.getTime()));

        if (genStrings.containsAll(strings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
    }
}
