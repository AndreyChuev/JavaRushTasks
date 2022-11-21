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
            ConsoleHelper.writeMessage("Сервер запущен!");
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

        private static final Message NAME_REQUEST_MESSAGE = new Message(MessageType.NAME_REQUEST,
                "Здравствуйте, введите имя:");
        private static final Message NAME_ACCEPTED_MESSAGE = new Message(MessageType.NAME_ACCEPTED,
                "Имя принято, вы добваленны в чат");

        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            connection.send(NAME_REQUEST_MESSAGE);
            Message response = connection.receive();
            if (response.getType() == MessageType.USER_NAME) {
                String name = response.getData();
                if (name == null || name.isEmpty() || connectionMap.containsKey(name)) {
                    return serverHandshake(connection);
                } else {
                    connectionMap.put(name, connection);
                    connection.send(NAME_ACCEPTED_MESSAGE);
                    return name;
                }
            } else {
                return serverHandshake(connection);
            }
        }
    }
}
