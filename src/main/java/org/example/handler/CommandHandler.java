package org.example.handler;

import org.example.common.CommandData;
import org.example.exeption.ProccesExeption;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class CommandHandler extends AbstractHandler implements Runnable{

    private final Socket clientSocket;

    public CommandHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    protected void proccesResponse(byte[] response, CommandData commandData, DataInputStream inputStream, OutputStream outputStream) throws IOException {
        outputStream.write(response);
        outputStream.flush();
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            OutputStream outputStream = clientSocket.getOutputStream();
            handle(inputStream,outputStream);
        }
        catch (IOException e){
            System.out.println("IOExeption:" + e.getMessage());
        }
        catch (ProccesExeption e){
            System.out.println("ProccesExeption:" + e.getMessage());
        }   finally {
            try {
                clientSocket.close();
                System.out.println("Socket closed");
            } catch (IOException e) {
                System.out.println("IOExeption:" + e.getMessage());;
            }
        }
    }

}
