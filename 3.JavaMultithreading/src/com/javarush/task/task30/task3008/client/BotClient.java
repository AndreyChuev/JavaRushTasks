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
            super.processIncomingMessage(text);
            if (text.contains(":")) {
                String[] splitText = text.split(":");
                String name = splitText[0].trim();
                String data = splitText[1].trim();

                String pattern = getPattern(data);
                if (pattern != null) {
                    Calendar calendar = new GregorianCalendar();
                    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                    String message = String.format("Информация для %s: %s", name, dateFormat.format(calendar.getTime()));
                    sendTextMessage(message);
                }
            }
        }

        private String getPattern(String text) {
            if (text.equals("дата")) {
                return "d.MM.YYYY";
            } else if (text.equals("день")) {
                return "d";
            } else if (text.equals("месяц")) {
                return "MMMM";
            } else if (text.equals("год")) {
                return "YYYY";
            } else if (text.equals("время")) {
                return "H:mm:ss";
            } else if (text.equals("час")) {
                return "H";
            } else if (text.equals("минуты")) {
                return "m";
            } else if (text.equals("секунды")) {
                return "s";
            } else {
                return null;
            }
        }

    }
}
