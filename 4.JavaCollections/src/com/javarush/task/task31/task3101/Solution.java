package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/* 
Проход по дереву файлов
D:\IDEA_project\JavaRushTasks\4.JavaCollections\src\com\javarush\task\task31\task3101\renameMe.txt
C:\Users\Андрей\Desktop\Подопытный
*/

public class Solution {
    public static void main(String[] args) {
        File targetDirectory = new File(args[0]);
        File resultFile = new File(args[1]);
        String newNameBeach = resultFile.getParent() + "\\allFilesContent.txt";
        File dest = new File(newNameBeach);
        if (FileUtils.isExist(dest)) {
            FileUtils.deleteFile(dest);
        }
        FileUtils.renameFile(resultFile,dest);

        StringBuilder builder = new StringBuilder();
        for (File targetFile : searchFiles(targetDirectory, new ArrayList<>())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))){
                while (reader.ready())
                    builder.append(reader.readLine());
                builder.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dest))){
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<File> searchFiles(File directory, ArrayList<File> list) {
        File[] filesArray = directory.listFiles();
        if (filesArray != null) {
            for (File file : filesArray) {
                if (file.isDirectory())
                    searchFiles(file,list);
                else if (file.length() <= 50)
                    list.add(file);
            }
        }
        return list;
    }
}
