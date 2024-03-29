package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap;

    static {
        connectionMap = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт:");
        int port = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("Сервер запущен! " + serverSocket.getLocalSocketAddress());
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (Exception e) {
            System.out.println("Внутренняя ошибка сервера " + e.getMessage());
        }
    }

    public static void sendBroadcastMessage(Message message) {
        for (String name : connectionMap.keySet()) {
            Connection connection = connectionMap.get(name);
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения");
            }
        }
    }

    private static class Handler extends Thread {

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String name = null;
            try {
                ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом " + socket.getRemoteSocketAddress());
                Connection connection = new Connection(socket);
                name = serverHandshake(connection);
                Message message = new Message(MessageType.USER_ADDED, name);
                sendBroadcastMessage(message);
                notifyUsers(connection, name);
                serverMainLoop(connection, name);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            } finally {
                Connection connection = null;
                try {
                    if (name != null) {
                        connection = connectionMap.remove(name);
                        Message message = new Message(MessageType.USER_REMOVED, name);
                        sendBroadcastMessage(message);
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (IOException e) {
                    ConsoleHelper.writeMessage("Ошибка при закрытии ресурсов " + e.getMessage());
                }
                ConsoleHelper.writeMessage("Соеденение закрыто");
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message receivedMessage = connection.receive();
                if (receivedMessage.getType() == MessageType.TEXT) {
                    Message newMessage = new Message(MessageType.TEXT, userName + ": " + receivedMessage.getData());
                    sendBroadcastMessage(newMessage);
                } else {
                    ConsoleHelper.writeMessage("Ошибка при отправке сообщения");
                }
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message greeting = new Message(MessageType.NAME_REQUEST, "Введите имя:");
            connection.send(greeting);

            Message response = connection.receive();
            if (response.getType() == MessageType.USER_NAME) {
                String name = response.getData();
                if (name == null || name.isEmpty() || connectionMap.containsKey(name)) {
                    return serverHandshake(connection);
                } else {
                    connectionMap.put(name, connection);
                    Message accepted = new Message(MessageType.NAME_ACCEPTED,"Имя принято, вы добваленны в чат");
                    connection.send(accepted);
                    return name;
                }
            } else {
                return serverHandshake(connection);
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName)) {
                    Message message = new Message(MessageType.USER_ADDED, name);
                    connection.send(message);
                }
            }
        }
    }
}
