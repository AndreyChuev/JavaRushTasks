package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Paths;

public class ZipAddCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Добавление файла в архив");
        ZipFileManager fileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Введите абсолютный путь к файлу:");
        String rawPath = ConsoleHelper.readString();
        fileManager.addFile(Paths.get(rawPath));
        ConsoleHelper.writeMessage("Файл добавлен!");
    }
}
