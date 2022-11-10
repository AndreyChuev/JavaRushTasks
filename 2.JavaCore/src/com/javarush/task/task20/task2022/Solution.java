package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String filename;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.filename = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
//        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(filename, true);
//        in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution save = new Solution("D:\\IDEA_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2022\\temp.bin");
        String testString = "Данные 1";
        save.writeObject(testString);

        File file = File.createTempFile("temp",null);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));

        outputStream.writeObject(save);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));

        Solution load = (Solution) inputStream.readObject();
        String testString2 = "Данные 2";
        load.writeObject(testString2);
    }
}
