package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorageStrategy implements StorageStrategy {

    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        for (Map.Entry<Long, String> entry : data.entrySet()) {
            if (effectiveEquals(entry.getValue(), value))
                return entry.getKey();
        }
        return null;
    }

    private boolean effectiveEquals(Object o1, Object o2) {
        return o1.hashCode() == o2.hashCode() && o1.equals(o2);
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
