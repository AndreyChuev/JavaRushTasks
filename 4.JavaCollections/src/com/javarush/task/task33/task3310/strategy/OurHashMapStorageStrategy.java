package com.javarush.task.task33.task3310.strategy;

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

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry entry = table[bucketIndex];
        if (entry == null) {
            createEntry(hash, key, value, bucketIndex);
        } else {
            insertEntry(table[bucketIndex], new Entry(hash, key, value, null));
        }

        if (++size > threshold) {
            resize(table.length * 2);
        }
    }

    private void insertEntry(Entry current, Entry target) {
        if (current.hash == target.hash && current.key.equals(target.key)) {
            current.value = target.value;
        } else if (current.next == null) {
            current.next = target;
        } else {
            insertEntry(current.next, target);
        }
    }

    /**
     * Идиотизм задания, добавляет new Entry в пустой бакет
     * @param hash хэш ключа
     * @param key значение ключа
     * @param value значение
     * @param bucketIndex индекс бакета в таблице
     */
    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new Entry(hash, key, value, null);
        } else {
            throw new RuntimeException("Adding entry to a non-empty bucket is prohibited!");
        }
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

    void resize(int newCapacity) {
        final int MAXIMUM_CAPACITY = 1073741824;
        int oldCapacity = table.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;

        float calcThreshold = (float) newCapacity * loadFactor;
        threshold = newCapacity < MAXIMUM_CAPACITY && calcThreshold < (float) MAXIMUM_CAPACITY ?
                (int) calcThreshold : Integer.MAX_VALUE;
    }

    private void transfer(Entry[] newTable) {
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];
            table[i] = null;
            while (entry != null) {
                Entry next = entry.next;
                int index = indexFor(entry.hash, newTable.length);
                entry.next = newTable[index];
                newTable[index] = entry;
                entry = next;
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
