package de.telran.lesson3.exeption_layer.exeptions;

public class EntityValidationException extends RuntimeException{
    public EntityValidationException(String message) {
        super(message);
    }
}
