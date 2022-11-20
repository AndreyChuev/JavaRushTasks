package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/* 
ClassLoader - что это такое?
*/
//TODO изменить Path на File
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);

    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> animals = new HashSet<>();
        PathClassLoader loader = new PathClassLoader();

//        if (pathToAnimals.startsWith("/"))
//            pathToAnimals = pathToAnimals.substring(1);
        File[] files = null;
        try {
            files = new File(URLDecoder.decode(pathToAnimals, "UTF-8")).listFiles(File::isFile);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        try {
            for (File file : files) {
                Class<?> clazz = loader.findClass(file.getAbsolutePath());
                if (Animal.class.isAssignableFrom(clazz)) {
                    try {
                        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
                        for (Constructor<?> constructor : constructors) {
                            if (constructor.getModifiers() == Modifier.PUBLIC && constructor.getParameterCount() == 0) {
                                animals.add((Animal) constructor.newInstance());
                            }
                        }
                    } catch (InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        return animals;
    }



//    private static File[] getPathClassesList(String filePath) {
//        if (filePath.startsWith("/"))
//            filePath = filePath.substring(1);
//        try {
//            return new File(URLDecoder.decode(filePath, "UTF-8")).listFiles(File::isFile);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//    }



    public static class PathClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = null;
            try {
                bytes = Files.readAllBytes(Paths.get(name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bytes == null)
                throw new ClassNotFoundException("Not found class: " + name);
            return defineClass(null, bytes, 0, bytes.length);
        }
    }
}
