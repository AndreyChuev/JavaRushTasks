package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) {
//        System.out.println(new Solution(4));
        try {
            File tempFile = File.createTempFile("temp", null);
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(tempFile));
                 ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(tempFile))){
                Solution savedObject = new Solution(23);
                outputStream.writeObject(savedObject);

                Solution loadedObject = (Solution) inputStream.readObject();

                System.out.println(savedObject.equals(loadedObject));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }

    @Override
    public int hashCode() {
        return 31 * string.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return false;
        if (obj == null || getClass() != obj.getClass()) return false;

        Solution solution = (Solution) obj;

        return string != null && solution.string != null && string.equals(solution.string);
    }
}
