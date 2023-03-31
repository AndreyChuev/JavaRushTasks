package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {

    private final OriginalRetriever originalRetriever;
    private final LRUCache<Long, Object> cache;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
        cache = new LRUCache<>(10);
    }

    @Override
    public Object retrieve(long id) {
        Object result = cache.find(id);
        if (result == null) {
            result = originalRetriever.retrieve(id);
            cache.set(id, result);
        }
        return result;
    }
}
