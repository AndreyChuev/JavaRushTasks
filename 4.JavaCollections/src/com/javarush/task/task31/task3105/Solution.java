package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив

D:\Target\car.jpg
D:\Target\CarsPhoto.zip
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //Считывание аргументов
        String file = args[0];
        String zip = args[1];

        Path filePath = Paths.get(file);
        String fileName = "new/" + filePath.getFileName().toString();

        /*
        * Создаётся массив для считывание уже записанных entry
        * */
        ArrayList<ZipFullEntry> entries = new ArrayList<>();
        try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream(zip))){
            ZipEntry entry;
            while ((entry = zipInput.getNextEntry()) != null) {
                if (entry.getName().endsWith(fileName)) continue;
                ZipFullEntry zipFullEntry = new ZipFullEntry();
                zipFullEntry.entry = entry;
                ByteArrayOutputStream entryDamp = new ByteArrayOutputStream();
                while (zipInput.available() != 0) {
                    byte[] buffer = new byte[1024];
                    int read = zipInput.read(buffer);
                    if (read != -1) entryDamp.write(buffer,0,read);
                }
                zipFullEntry.byteEntry = entryDamp;
                entries.add(zipFullEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zip))){
            zipOutputStream.putNextEntry(new ZipEntry(fileName));
            Files.copy(filePath,zipOutputStream);

            for (ZipFullEntry entry : entries) {
                zipOutputStream.putNextEntry(new ZipEntry(entry.entry.getName()));
                zipOutputStream.write(entry.byteEntry.toByteArray());
                zipOutputStream.closeEntry();
            }
        }
    }

    public static class ZipFullEntry {
        ByteArrayOutputStream byteEntry;
        ZipEntry entry;

        public ZipFullEntry(ByteArrayOutputStream byteEntry, ZipEntry entry) {
            this.byteEntry = byteEntry;
            this.entry = entry;
        }

        public ZipFullEntry() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ZipFullEntry that = (ZipFullEntry) o;
            return Objects.equals(byteEntry, that.byteEntry) && Objects.equals(entry, that.entry);
        }

        @Override
        public int hashCode() {
            return Objects.hash(byteEntry, entry);
        }
    }
}
