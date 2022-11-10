package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1916\first.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task19\task1916\second.txt
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader readerFirstFile = new BufferedReader(new FileReader(console.readLine()));
             BufferedReader readerSecondFile = new BufferedReader(new FileReader(console.readLine()))) {
            ArrayList<String> first = new ArrayList<>();
            ArrayList<String> second = new ArrayList<>();

            while (readerFirstFile.ready())
                first.add(readerFirstFile.readLine());

            while (readerSecondFile.ready())
                second.add(readerSecondFile.readLine());

            while (first.size() > 0 && second.size() > 0) {
                if (first.get(0).equals(second.get(0))) {
                    lines.add(new LineItem(Type.SAME, first.get(0)));
                    first.remove(0);
                    second.remove(0);
                } else if (first.get(0).equals(second.get(1))) {
                    lines.add(new LineItem(Type.ADDED, second.get(0)));
                    second.remove(0);
                } else {
                    lines.add(new LineItem(Type.REMOVED, first.get(0)));
                    first.remove(0);
                }
            }

            if (first.isEmpty()) {
                while (!second.isEmpty()) {
                    lines.add(new LineItem(Type.ADDED, second.get(0)));
                    second.remove(0);
                }
            }

            if (second.isEmpty()) {
                while (!first.isEmpty()) {
                    lines.add(new LineItem(Type.REMOVED, first.get(0)));
                    first.remove(0);
                }
            }


            lines.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Всё пропало!");
            e.printStackTrace();
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type + " " + line;
        }
    }
}
