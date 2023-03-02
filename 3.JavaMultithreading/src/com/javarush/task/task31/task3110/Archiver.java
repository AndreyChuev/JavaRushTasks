package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.WrongZipFileException;

import java.io.IOException;

public class Archiver {

    public static void main(String[] args) throws Exception {
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        } while (operation != Operation.EXIT);
    }

    public static Operation askOperation() throws IOException {
        StringBuilder menuBuilder = new StringBuilder("Выберите операцию:\n");

        for (Operation value : Operation.values()) {
            menuBuilder.append(String.format("%d - %s%n", value.ordinal(), value.description));
        }

        ConsoleHelper.writeMessage(menuBuilder.toString());
        return Operation.values()[ConsoleHelper.readInt()];
    }
}
