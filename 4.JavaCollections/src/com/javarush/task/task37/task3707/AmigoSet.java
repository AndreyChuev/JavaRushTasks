package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {

    // Заглушка
    private static final Object PRESENT = new Object();

    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max((int) Math.ceil(collection.size() / .75f), 16));
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public Spliterator<E> spliterator() {
        return map.keySet().spliterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet<E> newSet = (AmigoSet<E>) super.clone();
            newSet.map = (HashMap<E, Object>) map.clone();
            return newSet;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        out.writeInt(capacity);

        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        out.writeFloat(loadFactor);

        out.writeInt(map.size());

        for (E e : map.keySet()) {
            out.writeObject(e);
        }

    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        int size = in.readInt();

        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i < size; i++) {
            map.put((E) in.readObject(), PRESENT);
        }
    }

//    public static void main(String[] args) {
//        AmigoSet<String> set1 = new AmigoSet<>();
//        set1.add("info1");
//        set1.add("info2");
//
//        AmigoSet<String> set2 = new AmigoSet<>(set1);
//
//        System.out.println("set1 == set2 -> " + set1.equals(set2));
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//        try (ObjectOutputStream os = new ObjectOutputStream(outputStream)){
//            os.writeObject(set1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        AmigoSet<String> set3 = null;
//
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//        try (ObjectInputStream is = new ObjectInputStream(inputStream)) {
//            set3 = (AmigoSet<String>) is.readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("set1 == set3 -> " + Objects.requireNonNull(set3).equals(set1));
//    }
}
