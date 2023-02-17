package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10_000;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private FileBucket[] table = createTable(DEFAULT_INITIAL_CAPACITY);
    private int size;
    private long maxBucketSize = 2_000_000L;


    private static int hash(Long k) {
        int hash;
        return k == null ? 0 : (hash = k.hashCode()) ^ (hash >>> 16);
    }


    private static int indexFor(int hash, int length) {
        return hash & length - 1;
    }


    private static FileBucket[] createTable(int length) {
        FileBucket[] table = new FileBucket[length];
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
        return table;
    }


    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry target = new Entry(hash, key, value, null);
        table[bucketIndex].putEntry(target);

        // Баг в задаче 9
        size++;

        if (table[bucketIndex].getFileSize() > DEFAULT_BUCKET_SIZE_LIMIT) {
            int newCapacity = table.length * 2;
            if (newCapacity > 0) {
                resize(newCapacity);
            }
        }
    }


    private void resize(int newCapacity) {
        FileBucket[] newTable = createTable(newCapacity);
        for (FileBucket bucket : table) {
            for (Entry current = bucket.getEntry(); current != null; current = current.next) {
                Entry copy = new Entry(current.hash, current.key, current.value, null);
                int newIndex = indexFor(current.hash, newTable.length);
                newTable[newIndex].putEntry(copy);
            }
            bucket.remove();
        }
        table = newTable;
    }


    @Override
    public boolean containsKey(Long key) {
        return getValue(key) != null;
    }


    @Override
    public boolean containsValue(String value) {
        return getKey(value) != null;
    }


    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        addEntry(hash, key, value, index);
    }


    @Override
    public Long getKey(String value) {
        if (size == 0)
            return null;

        for (FileBucket bucket : table) {
            if (bucket.getFileSize() == 0L)
                continue;

            for (Entry current = bucket.getEntry(); current != null; current = current.next) {
                if (current.value.hashCode() == value.hashCode() && current.value.equals(value)) {
                    return current.key;
                }
            }
        }
        return null;
    }


    @Override
    public String getValue(Long key) {
        if (size == 0)
            return null;

        int index = indexFor(hash(key), table.length);
        for (Entry current = table[index].getEntry(); current != null; current = current.next) {
            if (current.key.equals(key)) {
                return current.value;
            }
        }
        return null;
    }
}
