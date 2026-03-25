package org.example.exeption;

public class ProccesExeption extends RuntimeException{
    public ProccesExeption() {
    }

    public ProccesExeption(String message) {
        super(message);
    }

    public ProccesExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ProccesExeption(Throwable cause) {
        super(cause);
    }

    public ProccesExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
