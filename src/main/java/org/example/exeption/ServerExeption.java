package org.example.exeption;

public class ServerExeption extends RuntimeException{
    public ServerExeption() {
    }

    public ServerExeption(String message) {
        super(message);
    }

    public ServerExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerExeption(Throwable cause) {
        super(cause);
    }

    public ServerExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
