package com.javarush.task.task16.task1630;

import java.io.*;
import java.util.ArrayList;

/* 
Последовательный вывод файлов
*/

//D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task16\task1630\fileOne.txt
//D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task16\task1630\fileTwo.txt

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            System.out.println("Ведите имя первого файла:");
            firstFileName = reader.readLine();
//            firstFileName = "D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task16\\task1630\\fileOne.txt";
//            System.out.println("Ведите имя второго файла:");
            secondFileName = reader.readLine();
//            secondFileName = "D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task16\\task1630\\fileTwo.txt";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        private String fileName;
        private String content = "";

        @Override
        public void setFileName(String fullFileName) {
            fileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            return content;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                while (reader.ready()) {
                    content += reader.readLine() + " ";
                }
            } catch (FileNotFoundException e) {
                System.out.println(getName() + " Файл " + fileName + " ---> Не найден!");
            } catch (IOException e) {
                System.out.println("Ошибка ввода / вывода");
                e.printStackTrace();
            }
        }
    }
}
