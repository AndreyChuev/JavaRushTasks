package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("D:\\IDEA_project\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task32\\task3210\\text.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        if (is == null) return new StringWriter();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        StringWriter stringWriter = new StringWriter();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringWriter.write(line);
        }
        return stringWriter;
    }
}
