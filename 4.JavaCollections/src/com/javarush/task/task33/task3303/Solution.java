package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName), clazz);
    }

    public static void main(String[] args) throws IOException {
//        Cat cat = new Cat();
//        cat.name = "Марти";
//        cat.age = 1;
//
//        ObjectMapper mapper = new ObjectMapper();
//        StringWriter writer = new StringWriter();
//        mapper.writeValue(writer,cat);
//        System.out.println(writer);
//
//        String file = "D:\\IDEA_project\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task33\\task3303\\marty.txt";
//        Cat marty = convertFromJsonToNormal(file, Cat.class);
//        System.out.println(marty);
    }

//    @JsonAutoDetect
//    public static class Cat {
//        public String name;
//        public int age;
//
//        @Override
//        public String toString() {
//            return "Cat{" +
//                    "name='" + name + '\'' +
//                    ", age=" + age +
//                    '}';
//        }
//    }
}
