package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        return measure(() -> strings.forEach(s -> ids.add(shortener.getId(s))));
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        return measure(() -> ids.forEach(id -> strings.add(shortener.getString(id))));
    }

    private long measure(Runnable runnable) {
        Date start = new Date();
        runnable.run();
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10_000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> idsShortener1 = new HashSet<>();
        long timeToGetIdsShortener1 = getTimeToGetIds(shortener1, origStrings, idsShortener1);

        Set<Long> idsShortener2 = new HashSet<>();
        long timeToGetIdsShortener2 = getTimeToGetIds(shortener2, origStrings, idsShortener2);

        Assert.assertTrue(timeToGetIdsShortener1 > timeToGetIdsShortener2);


        Set<String> stringsShortener1 = new HashSet<>();
        long timeToGetStringsShortener1 = getTimeToGetStrings(shortener1, idsShortener1, stringsShortener1);

        Set<String> stringsShortener2 = new HashSet<>();
        long timeToGetStringsShortener2 = getTimeToGetStrings(shortener2, idsShortener2, stringsShortener2);
        Assert.assertEquals((float) timeToGetStringsShortener1, (float) timeToGetStringsShortener2, 30f);
    }
}
