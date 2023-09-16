package de.telran.lesson3.exeption_layer.exceptionIAmTeapot;

public class ExceptionIAmTeapot extends RuntimeException{
    public ExceptionIAmTeapot(String message) {
        super(message);
    }

    public ExceptionIAmTeapot(String message, Throwable cause) {
        super(message, cause);
    }
}
