package com.javarush.task.task18.task1806;

import java.io.*;

/* 
Исправить ошибки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("c:/data.txt");
        // Создаем поток-записи-байт-в-файл
        OutputStream outputStream = new FileOutputStream("c:/result.txt");

        if (inputStream.available() > 0) {
            //читаем весь файл одним куском
            byte[] buffer = new byte[inputStream.available()];
            int count = inputStream.read(buffer);
            outputStream.write(buffer, 0, count);
        }

        inputStream.close();
        outputStream.close();
    }
}
