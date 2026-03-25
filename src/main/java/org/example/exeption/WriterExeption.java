package org.example.exeption;

public class WriterExeption extends RuntimeException{
    public WriterExeption() {
    }

    public WriterExeption(String message) {
        super(message);
    }

    public WriterExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public WriterExeption(Throwable cause) {
        super(cause);
    }

    public WriterExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
