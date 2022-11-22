package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient client = new BotClient();
        client.run();
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public class BotSocketThread extends Client.SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String text) {
            if (text.contains(":")) {
                super.processIncomingMessage(text);
                String[] splitText = text.split(":");
                String name = splitText[0].trim();
                String data = splitText[1].trim();

                DateCommand command = DateCommand.getDateCommand(data);
                if (command != DateCommand.UNSPECIFIED) {
                    Calendar calendar = new GregorianCalendar();
                    SimpleDateFormat formatter = new SimpleDateFormat(command.getFormat());
                    String message = "Информация для " + name
                            + ": " + formatter.format(calendar.getTime());
                    super.processIncomingMessage(message);
                }
            }
        }



        private enum DateCommand {
            DATE("дата", "d.MM.YYYY"),
            DAY("день", "d"),
            MONTH("месяц", "MMMM"),
            YEAR("год", "YYYY"),
            TIME("время", "H:mm:ss"),
            HOUR("час", "H"),
            MINUTES("минуты", "m"),
            SECONDS("секунды", "s"),
            UNSPECIFIED("", "");

            private final String command;
            private final String format;

            DateCommand(String command, String format) {
                this.command = command;
                this.format = format;
            }

            public String getFormat() {
                return format;
            }

            private static DateCommand getDateCommand(String textCommand) {
                for (DateCommand dateCommand : values()) {
                    if (dateCommand.command.equals(textCommand)) {
                        return dateCommand;
                    }
                }
                return UNSPECIFIED;
            }
        }
    }
}
