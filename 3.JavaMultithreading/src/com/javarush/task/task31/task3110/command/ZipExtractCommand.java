package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand {

    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Распаковка архива");
            ZipFileManager zipFileManager = getZipFileManager();
            ConsoleHelper.writeMessage("Ведите пусть распаковки:");
            String rawPath = ConsoleHelper.readString();
            zipFileManager.extractAll(Paths.get(rawPath));
            ConsoleHelper.writeMessage("Распаковка завершена!");
        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }

    }
}
