package org.example;

import org.example.exeption.ServerExeption;
import org.example.handler.CommandHandler;

import java.io.IOException;
import java.net.ServerSocket;


public class server {
    private final int port;

    public server(int port) {
        this.port = port;
    }

    public void start() {
        try (var serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            System.out.println("Server started on port " + port);
            while (true) {
                var clientSocket = serverSocket.accept();
                new Thread(new CommandHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new ServerExeption(e);
        }
    }

}
