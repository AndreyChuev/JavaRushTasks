package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Objects;

public class OurHashMapStorageStrategy implements StorageStrategy {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = .75f;

    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    static int hash(Long k) {
        int hash;
        return k == null ? 0 : (hash = k.hashCode()) ^ (hash >>> 16);
    }

    private static int indexFor(int hash, int length) {
        return hash & length - 1;
    }

    Entry getEntry(Long key) {
        int keyHash = hash(key);
        int index = indexFor(keyHash, table.length);
        Entry entry = table[index];
        while (entry != null && !(entry.hash == keyHash && entry.key.equals(key))) {
            entry = entry.next;
        }
        return entry;
    }

    private void transfer(Entry[] newTable) {
        for (int i = 0; i < table.length; i++) {
            Entry e = table[i];
            table[i] = null;
            while (e != null) {
                Entry next = e.next;
                int index = indexFor(e.hash, newTable.length);
                e.next = newTable[index];
                newTable[index] = e;
                e = next;
            }
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return false;
    }

    @Override
    public void put(Long key, String value) {

    }

    @Override
    public Long getKey(String value) {
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null;
    }
}
