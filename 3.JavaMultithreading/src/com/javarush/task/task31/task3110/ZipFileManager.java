package com.javarush.task.task31.task3110;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {

    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        try (ZipOutputStream zipOS = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            ZipEntry entry = new ZipEntry(source.getFileName().toString());
            zipOS.putNextEntry(entry);

            try (InputStream fileIS = Files.newInputStream(source)){
                byte[] buffer = new byte[1024];
                while (fileIS.available() > 0) {
                    int bytesRead = fileIS.read(buffer);
                    zipOS.write(buffer, 0, bytesRead);
                    Arrays.fill(buffer, (byte) 0);
                }
            } finally {
                zipOS.closeEntry();
            }

        }
    }
}
