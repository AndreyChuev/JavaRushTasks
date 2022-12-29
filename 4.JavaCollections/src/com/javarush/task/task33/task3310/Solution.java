package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.testStrategy(new HashMapStorageStrategy(), 10_000);
    }

    public void testStrategy(StorageStrategy strategy, long elementNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> genStrings = Stream.generate(Helper::generateRandomString)
                .limit(elementNumber)
                .collect(Collectors.toSet());

        Shortener shortener = new Shortener(strategy);

        Set<Long> ids = measureTime(() -> genStrings.stream().map(shortener::getId).collect(Collectors.toSet()));
        Set<String> strings = measureTime(() -> ids.stream().map(shortener::getString).collect(Collectors.toSet()));

        if (genStrings.equals(strings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    private <T> T measureTime(Supplier<T> supplier) {
        Date start = new Date();
        T result = supplier.get();
        Date finish = new Date();
        Helper.printMessage(String.valueOf(finish.getTime() - start.getTime()));
        return result;
    }

    public Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream().map(shortener::getId).collect(Collectors.toSet());
    }

    public Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream().map(shortener::getString).collect(Collectors.toSet());
    }
}
