package com.javarush.task.pro.task15.task1504;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) {
/*
        var fileOne = Paths.get("C:\\Users\\Андрей\\Desktop\\fileOne.txt");
        var fileTwo = Paths.get("C:\\Users\\Андрей\\Desktop\\fileTwo.txt");
*/
        try (Scanner scanner = new Scanner(System.in);
             var input = Files.newInputStream(Paths.get(scanner.nextLine()));
             var output = Files.newOutputStream(Paths.get(scanner.nextLine()))) {
/*
            try (Scanner scanner = new Scanner(System.in);
                 var input = Files.newInputStream(fileOne);
                 var output = Files.newOutputStream(fileTwo)) {
*/
                int[] buffer = new int[input.available()]; //массив для записи файла
                int[] temp = new int[buffer.length]; //временный массив для перестановки

                //цикл записи данных с потока в буфер
                for (int i = 0; input.available() > 0; i++) {
                    buffer[i] = input.read();
                }

                //перестановка байтов
                int[] it = calcIteration(buffer);
                for (int i = 0, j = 1; i < it[1]; ) {
                    if (j == it[1]) {
                        temp[i] = buffer[i];
                        break;
                    }
                    temp[i] = buffer[j];
                    temp[j] = buffer[i];
                    j += 2;
                    i += 2;
                }

                //запись байтов в файл
                for (int i = 0; i < temp.length; i++) {
                    output.write(temp[i]);
                }

                System.out.println("Готово!");

            } catch (IOException e) {
                System.out.println("Файл не найден!");
                e.printStackTrace();
            }

        }

    /*
    *   Метод возвращает массив int {a , b}
    *   а - количество целых итераций
    *   b - значение для второй переменной
    */
    private static int[] calcIteration (int[] fileArray) {
        int[] result = {0, 0};
        result[0] = fileArray.length - (fileArray.length % 2);
        result[1] = fileArray.length;
        //System.out.println(fileArray.length % 2);
        //System.out.println(Arrays.toString(result));
        return result;
    }
/*
    private static void testOne () {
        int[] test = new int[19];
        int[] i = calcIteration(test);
        System.out.println("Целых итераций " + i[0]);
        System.out.println("Значение второй переменной " + i[1]);
    }
*/

}

