package com.javarush.task.task30.task3008.client;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient client = new BotClient();
        client.run();
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + Math.abs(Math.random() * 100);
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


    }
}
