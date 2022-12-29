package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int valuesCount = 0;
        for (List<V> value : map.values()) {
            valuesCount += value.size();
        }
        return valuesCount;
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key))
            map.put(key, new ArrayList<>());

        List<V> values = map.get(key);
        V result = getLast(values);

        if (values.size() < repeatCount)
            values.add(value);
        else
            removeFirst(values).add(value);

        return result;
    }

    private List<V> removeFirst(List<V> values) {
        if (values.size() != 0)
            values.remove(0);
        return values;
    }

    private V getLast(List<V> list) {
        return list.size() != 0 ? list.get(list.size() - 1) : null;
    }

    @Override
    public V remove(Object key) {
        if (key == null || !map.containsKey(key)) return null;
        List<V> values = map.get(key);
        V val = values.remove(0);
        if (values.size() == 0)
            map.remove(key);
        return val;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> result = new ArrayList<>(size());
        for (List<V> vList : map.values()) {
            result.addAll(vList);
        }
        return result;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> vList : map.values()) {
            if (vList.contains(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}