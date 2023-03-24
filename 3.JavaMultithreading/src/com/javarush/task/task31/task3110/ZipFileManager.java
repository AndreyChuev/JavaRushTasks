package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {

    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception {
        if (!Files.exists(zipFile.getParent())) {
            Files.createDirectory(zipFile.getParent());
        }

        ZipOutputStream zipOS = new ZipOutputStream(Files.newOutputStream(zipFile));

        if (Files.isRegularFile(source)) {
            addNewZipEntry(zipOS, source.getParent(), source.getFileName());
        } else if (Files.isDirectory(source)) {
            FileManager manager = new FileManager(source);
            List<Path> fileNames = manager.getFileList();
            for (Path path : fileNames) {
                addNewZipEntry(zipOS, path.getParent(), path.getFileName());
            }
        } else {
            throw new PathIsNotFoundException();
        }
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception {
        try (InputStream inputStream = Files.newInputStream(filePath.resolve(fileName))) {

            ZipEntry entry = new ZipEntry(fileName.toString());
            zipOutputStream.putNextEntry(entry);

            copyData(inputStream, zipOutputStream);

        } finally {
            zipOutputStream.closeEntry();
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception {
        byte[] buffer = new byte[1024];
        while (in.available() > 0) {
            int bytesRead = in.read(buffer);
            out.write(buffer, 0, bytesRead);
        }
    }
}
