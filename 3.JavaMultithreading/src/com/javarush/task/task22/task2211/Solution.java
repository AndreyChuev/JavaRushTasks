package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/* 
Смена кодировки
D:\IDEA_project\JavaRushTasks\3.JavaMultithreading\src\com\javarush\task\task22\task2211\W1251.txt
D:\IDEA_project\JavaRushTasks\3.JavaMultithreading\src\com\javarush\task\task22\task2211\UTF.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(args[0], Charset.forName("Windows-1251")));
//             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))){
//            while (reader.ready()) {
//                writer.write(reader.readLine());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (InputStream inputStream = new FileInputStream(args[0]);
             OutputStream outputStream = new FileOutputStream(args[1])){
            byte[] buffer = new byte[inputStream.available()];
            int read = inputStream.read(buffer);
            String fileContent = new String(buffer, "Windows-1251");
            buffer = fileContent.getBytes(StandardCharsets.UTF_8);
            outputStream.write(buffer);
        }
    }
}
