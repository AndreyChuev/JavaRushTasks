package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Собираем файл
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1825\file.txt.part3
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1825\file.txt.part1
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1825\file.txt.part2

*/

public class Solution {
    public static void main(String[] args) {
        TreeSet<Part> parts = new TreeSet<>(Part::compareTo);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                String line = reader.readLine();
                if (!line.equals("end"))
                    parts.add(new Part(line));
                else
                    break;
            }

            try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(parts.first().getNewFilePath()))){
                for (Part part : parts) {
                    output.write(part.getAllBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Part {
        private final String filePath;
        private final String newFilePath;
        private final int serialNumber;
        private byte[] bytes;

        public Part(String filePath) {
            Pattern pattern = Pattern.compile("\\.part\\d+");
            Matcher matcher = pattern.matcher(filePath);
            if (matcher.find()) {
                serialNumber = Integer.parseInt(matcher.group().substring(5));
                newFilePath = filePath.substring(0, matcher.start());
                this.filePath = filePath;
            } else {
                throw new IllegalArgumentException("Не правильный формат пути файла: " + filePath);
            }
            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(this.filePath))){
                bytes = new byte[input.available()];
                for (int i = 0; input.available() > 0; i++) {
                    bytes[i] = (byte) input.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getNewFilePath() {
            return newFilePath;
        }

        public int compareTo(Part part) {
            return serialNumber - part.serialNumber;
        }

        public byte[] getAllBytes() {
            return bytes;
        }
    }
}
