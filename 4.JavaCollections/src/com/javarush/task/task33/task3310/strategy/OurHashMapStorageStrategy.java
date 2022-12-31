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

    void resize() {
        final int MAXIMUM_CAPACITY = 1073741824;
        Entry[] oldTable = table;
        int oldCapacity = (oldTable == null) ? 0 : oldTable.length;
        int oldThreshold = threshold;
        int newCapacity, newThreshold = 0;
        if (oldCapacity > 0) {
            if (oldCapacity >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return;
            } else if ((newCapacity = oldCapacity << 1) < MAXIMUM_CAPACITY &&
                    oldCapacity >= DEFAULT_INITIAL_CAPACITY) {
                newThreshold = oldThreshold << 1;
            }
        } else if (oldThreshold > 0) {
            newCapacity = oldThreshold;
        } else {
            newCapacity = DEFAULT_INITIAL_CAPACITY;
            newThreshold = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThreshold == 0) {
            float ft = (float) newCapacity * loadFactor;
            newThreshold = (newCapacity < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
                    (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThreshold;
        Entry[] newTable = new Entry[newCapacity];
        table = newTable;
        if (oldTable != null) {
            for (int i = 0; i < oldCapacity; i++) {
                Entry entry;
                if ((entry = oldTable[i]) != null) {
                    oldTable[i] = null;
                    if (entry.next == null) {
                        newTable[entry.hash & (newCapacity - 1)] = entry;
                    } else {
                        Entry lowHead = null, lowTail = null;
                        Entry highHead = null, highTail = null;
                        Entry next;
                        do {
                            next = entry.next;
                            if ((entry.hash & oldCapacity) == 0) {
                                if (lowTail == null)
                                    lowHead = entry;
                                else
                                    lowTail.next = entry;
                                lowTail = entry;
                            } else {
                                if (highTail == null)
                                    highHead = entry;
                                else
                                    highTail.next = entry;
                                highTail = entry;
                            }
                        } while ((entry = next) != null);
                        if (lowTail != null) {
                            lowTail.next = null;
                            newTable[i] = lowHead;
                        } if (highTail != null) {
                            highTail.next = null;
                            newTable[i + oldCapacity] = highHead;
                        }
                    }
                }
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
