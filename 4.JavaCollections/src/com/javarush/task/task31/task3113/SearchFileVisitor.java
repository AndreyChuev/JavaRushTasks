package com.javarush.task.task31.task3113;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private long directorySize;
    private int folderCount = -1;
    private int fileCount;

    public long getDirectorySize() {
        return directorySize;
    }

    public int getFolderCount() {
        return folderCount;
    }

    public int getFileCount() {
        return fileCount;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            folderCount++;
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        directorySize += attrs.size();
        fileCount++;
        return super.visitFile(file, attrs);
    }
}
