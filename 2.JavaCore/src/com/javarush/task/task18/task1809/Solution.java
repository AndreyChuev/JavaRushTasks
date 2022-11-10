package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) {
        String[] paths = new String[2];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (int i = 0; i < paths.length; i++) {
                paths[i] = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            e.printStackTrace();
        }

        try (InputStream input = new FileInputStream(paths[0]);
            OutputStream output = new FileOutputStream(paths[1])){
            ArrayList<Integer> bytes = new ArrayList<>();
            while (input.available() > 0) {
                bytes.add(input.read());
            }
            Collections.reverse(bytes);
            for (Integer aByte : bytes) {
                output.write(aByte);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
            e.printStackTrace();
        }
    }
}
