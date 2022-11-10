package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();  //TODO add your code here


    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (!cache.containsKey(key))
            cache.put(key, clazz.getDeclaredConstructor(key.getClass()).newInstance(key));
        return cache.get(key);
    }

    @SuppressWarnings("unchecked")
    public boolean put(V obj) {
//        V resObj = null;
        try {
            Method getMethod = obj.getClass().getDeclaredMethod("getKey");
            getMethod.setAccessible(true);
            K key = (K) getMethod.invoke(obj);
            cache.put(key, obj);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
