package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName, partOfContent;
    private int minSize, maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        skip: {
            if (partOfName != null)
                if (!file.getFileName().toString().contains(partOfName))
                    break skip;
            if (partOfContent != null) {
                StringBuilder builder = new StringBuilder();
                Files.readAllLines(file).forEach(builder::append);
                if (!builder.toString().contains(partOfContent))
                    break skip;
            }
            if (minSize > 0 && maxSize > 0)
                if (!((content.length < maxSize) && (content.length > minSize)))
                    break skip;
            if (minSize > 0)
                if (!(content.length > minSize))
                    break skip;
            if (maxSize > 0)
                if (!(content.length < maxSize))
                    break skip;
            foundFiles.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
