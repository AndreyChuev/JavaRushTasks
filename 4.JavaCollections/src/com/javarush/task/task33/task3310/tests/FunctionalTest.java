package com.javarush.task.task33.task3310.tests;


import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        List<String> strings = new ArrayList<>();
        strings.add("testString");
        strings.add("testStringUni");
        strings.add("testString");

        List<Long> ids = strings.stream()
                .map(shortener::getId)
                .collect(Collectors.toList());


        Assert.assertNotEquals(ids.get(1), ids.get(0));
        Assert.assertNotEquals(ids.get(1), ids.get(2));

        Assert.assertEquals(ids.get(0), ids.get(2));

        List<String> storageStrings = ids.stream()
                .map(shortener::getString)
                .collect(Collectors.toList());

        Assert.assertEquals(strings, storageStrings);
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }

}
