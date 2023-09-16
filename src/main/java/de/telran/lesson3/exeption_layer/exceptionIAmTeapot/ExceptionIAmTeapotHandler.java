package de.telran.lesson3.exeption_layer.exceptionIAmTeapot;

import de.telran.lesson3.exeption_layer.customerException.CustomerExceptionField;
import de.telran.lesson3.exeption_layer.customerException.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionIAmTeapotHandler {
    @ExceptionHandler(value = ExceptionIAmTeapot.class)
    public ResponseEntity<Object> handleListCustomersIsEmptyException(ExceptionIAmTeapot exceptionIAmTeapot) {
        CustomerExceptionField customerExceptionField = new CustomerExceptionField(
                exceptionIAmTeapot.getMessage(), exceptionIAmTeapot.getCause(), HttpStatus.I_AM_A_TEAPOT
        );
        return new ResponseEntity<>(customerExceptionField, HttpStatus.I_AM_A_TEAPOT);
    }
}
