package com.javarush.task.task18.task1819;

import java.io.*;

/* 
Объединение файлов
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1819\first.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1819\second.txt
*/

public class Solution {
    public static void main(String[] args) {
        String firstFile;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(firstFile = reader.readLine());
        FileInputStream inputStream2 = new FileInputStream(reader.readLine());
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream((inputStream.available()) + inputStream2.available())) {
            while (inputStream2.available() > 0)
                byteArray.write(inputStream2.read());
            while (inputStream.available() > 0)
                byteArray.write(inputStream.read());
            byteArray.flush();
            try (FileOutputStream outputStream = new FileOutputStream(firstFile)){
                outputStream.write(byteArray.toByteArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
