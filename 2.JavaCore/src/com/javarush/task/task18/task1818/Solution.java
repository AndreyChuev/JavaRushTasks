package com.javarush.task.task18.task1818;

import java.io.*;

/* 
Два в одном
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1818\first.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1818\second.txt
D:\IDEA_project\JavaRushTasks\2.JavaCore\src\com\javarush\task\task18\task1818\third.txt
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream outputStream = new FileOutputStream(reader.readLine(),true);
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileInputStream inputStream2 = new FileInputStream(reader.readLine());
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream((inputStream.available()) + inputStream2.available())) {
            while (inputStream.available() > 0)
                byteArray.write(inputStream.read());
            while (inputStream2.available() > 0)
                byteArray.write(inputStream2.read());
            byteArray.flush();
            outputStream.write(byteArray.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
