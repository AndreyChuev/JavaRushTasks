package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {

    private volatile boolean clientConnected = false;

    protected Connection connection;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                wait();
                if (clientConnected) {
                    ConsoleHelper.writeMessage("Соеденение установленно");
                } else {
                    ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента");
                }

                while (clientConnected) {
                    String text = ConsoleHelper.readString();

                    if (text.equals("exit"))
                        break;

                    if (shouldSendTextFromConsole())
                        sendTextMessage(text);
                }
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Ошибка потока");
            }
        }
    }

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected void sendTextMessage(String text) {
        try {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        } catch (IOException e) {
            clientConnected = false;
            ConsoleHelper.writeMessage("Ошибка при отправке сообщения");
        }
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    public class SocketThread extends Thread {

        protected void processIncomingMessage(String text) {
            ConsoleHelper.writeMessage(text);
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.NAME_REQUEST) {
                    Message response = new Message(MessageType.USER_NAME, getUserName());
                    connection.send(response);
                } else if (receivedMessage.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.TEXT) {
                    processIncomingMessage(receivedMessage.getData());
                } else if (receivedMessage.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(receivedMessage.getData());
                } else if (receivedMessage.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(receivedMessage.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Пользователь " + userName + " присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник " + userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
    }
}
