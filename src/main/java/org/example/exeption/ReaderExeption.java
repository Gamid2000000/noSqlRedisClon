package org.example.exeption;

public class ReaderExeption extends RuntimeException{
    public ReaderExeption() {
    }

    public ReaderExeption(String message) {
        super(message);
    }

    public ReaderExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderExeption(Throwable cause) {
        super(cause);
    }

    public ReaderExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
