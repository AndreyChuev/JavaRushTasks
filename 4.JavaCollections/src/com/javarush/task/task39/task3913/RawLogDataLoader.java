package com.javarush.task.task39.task3913;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RawLogDataLoader {

    private final List<String> rawLogs;

    public RawLogDataLoader(Path dir) {
        if (Files.isDirectory(dir)) {
            rawLogs = loadLogs(dir);
        } else {
            throw new IllegalArgumentException(dir + " is not directory!");
        }
    }

    private List<String> loadLogs(Path dir) {
        try (Stream<Path> logFiles = Files.list(dir)){
            return logFiles
                    .filter(path -> path.getFileName().toString().endsWith(".log"))
                    .flatMap(this::readAllLogs)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<String> readAllLogs(Path path) {
        try {
            return Files.readAllLines(path).stream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getRawLogs() {
        return rawLogs;
    }
}
