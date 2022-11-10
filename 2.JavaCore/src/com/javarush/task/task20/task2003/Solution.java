package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task20\task2003\table.properties
*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        Properties properties = new Properties();
        for (Map.Entry<String, String> stringStringEntry : runtimeStorage.entrySet()) {
            properties.setProperty(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        properties.store(outputStream,"");
    }

    public static void load(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
            runtimeStorage.put((String) objectObjectEntry.getKey(),(String) objectObjectEntry.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fos = new FileInputStream(reader.readLine())) {
            load(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);
//        String file = "D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2003\\table.properties";
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        save(fileOutputStream);
//        fileOutputStream.close();
//
//        FileInputStream inputStream = new FileInputStream(file);
//        load(inputStream);
//        inputStream.close();
//
//        System.out.println(runtimeStorage);
    }
}
