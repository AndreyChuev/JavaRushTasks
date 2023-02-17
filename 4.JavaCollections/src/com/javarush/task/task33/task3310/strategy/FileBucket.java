package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;
import com.javarush.task.task33.task3310.Helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {

    private Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile(Helper.generateRandomString(), null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putEntry(Entry newEntry) {
        Entry entry = getEntry();

        if (entry == null) {
            entry = newEntry;
        } else {
            for (Entry current = entry; ; current = current.next) {
                if (current.hash == newEntry.hash && current.key.equals(newEntry.key)) {
                    current.value = newEntry.value;
                    break;
                } else if (current.next == null) {
                    current.next = newEntry;
                    break;
                }
            }
        }

        try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(path))){
            os.writeObject(entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Entry getEntry() {
        if (getFileSize() == 0L)
            return null;

        try (ObjectInputStream objectIS = new ObjectInputStream(Files.newInputStream(path))){
            return (Entry) objectIS.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
