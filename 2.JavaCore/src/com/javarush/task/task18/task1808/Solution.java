package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) {
        String[] paths = new String[3];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < paths.length; i++) {
                paths[i] = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            e.printStackTrace();
        }


        int sizeFile = 0;
        try (InputStream input = new FileInputStream(paths[0]);
        OutputStream output = new FileOutputStream(paths[1]);
        OutputStream output2 = new FileOutputStream(paths[2])) {
            sizeFile = input.available();
            byte[] fileOne;
            byte[] fileTwo;
            if (!(sizeFile % 2 == 0)) {
                fileOne = new byte[(int) Math.ceil(sizeFile / 2.0)];
                fileTwo = new byte[sizeFile - fileOne.length];
            } else {
                fileTwo = new byte[(int) Math.ceil(sizeFile / 2.0)];
                fileOne = new byte[sizeFile - fileTwo.length];
            }

            while (input.available() > 0) {
                int count = input.read(fileOne);
                int count2 = input.read(fileTwo);
                if (!(count == fileOne.length & count2 == fileTwo.length))
                    throw new IOException("Не верно считаны байты!");
                output.write(fileOne);
                output2.write(fileTwo);
                System.out.println("Запись выполнена успешно!");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка ввода/вывода!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        }
    }
}
