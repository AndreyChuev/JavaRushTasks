package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {

    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Удаление файла");
        ZipFileManager fileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Введите имя файла для удаления:");
        String targetFile = ConsoleHelper.readString();
        fileManager.removeFile(Paths.get(targetFile));
        ConsoleHelper.writeMessage("Файл удален");
    }
}
