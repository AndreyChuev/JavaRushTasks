package com.javarush.task.task33.task3310.tests;


import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FunctionalTest {

    public void testStorage(Shortener shortener) {
        List<String> strings = new ArrayList<>();
        strings.add("testString");
        strings.add("testStringUni");
        strings.add("testString");

        List<Long> ids = strings.stream()
                .map(shortener::getId)
                .toList();


        Assert.assertNotEquals(ids.get(1), ids.get(0));
        Assert.assertNotEquals(ids.get(1), ids.get(2));

        Assert.assertEquals(ids.get(0), ids.get(2));

        List<String> storageStrings = ids.stream()
                .map(shortener::getString)
                .toList();

        Assert.assertEquals(strings, storageStrings);
    }

    @Test
    public void testHashMapStorageStrategy() {
        testStorage(new Shortener(new HashMapStorageStrategy()));
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        testStorage(new Shortener(new OurHashBiMapStorageStrategy()));
    }

    @Test
    public void testFileStorageStrategy() {
        testStorage(new Shortener(new FileStorageStrategy()));
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        testStorage(new Shortener(new HashBiMapStorageStrategy()));
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        testStorage(new Shortener(new DualHashBidiMapStorageStrategy()));
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        testStorage(new Shortener(new OurHashBiMapStorageStrategy()));
    }

}
