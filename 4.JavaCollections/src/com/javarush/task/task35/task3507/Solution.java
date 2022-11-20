package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

/* 
ClassLoader - что это такое?
*/
//TODO изменить Path на File
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);

    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animals = new HashSet<>();
        PathClassLoader loader = new PathClassLoader();
        try {
            for (String path : getPathClassesList(pathToAnimals)) {
                Class<?> clazz = loader.loadClass(path);
                try {
                    Constructor<?>[] constructors = clazz.getDeclaredConstructors();
                    for (Constructor<?> constructor : constructors) {
                        if (constructor.getModifiers() == Modifier.PRIVATE) {
                            constructor.setAccessible(true);
                        }
                        if (constructor.getParameterCount() == 0) {
                            animals.add((Animal) constructor.newInstance());
                        }
                    }
                } catch (ClassCastException | IllegalAccessException ignore) {
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        return animals;
    }



    private static List<String> getPathClassesList(String filePath) {
        if (filePath.startsWith("/"))
            filePath = filePath.substring(1);

        String packagePath = normalizePackagePath(filePath);
        try (var pathStream = Files.walk(Path.of(filePath))){
            return pathStream
                    .filter(Files::isRegularFile)
                    .map(path -> packagePath + "." + path.getFileName().toString())
                    .map(s -> s = s.substring(0, s.lastIndexOf(".")))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Path not found: " + filePath);
    }

    private static String normalizePackagePath(String path) {
        String lastPack = path.substring(path.lastIndexOf("/") + 1);
        String pack = Solution.class.getPackage().getName();
        return pack + "." + lastPack;
    }



    public static class PathClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = null;
            try {
                bytes = Files.readAllBytes(Path.of(name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bytes == null)
                throw new ClassNotFoundException("Not found class: " + name);
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
