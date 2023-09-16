package de.telran.lesson3.exeption_layer.customerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {
   @ExceptionHandler(value = MyException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(MyException myException) {
        CustomerExceptionField customerExceptionField = new CustomerExceptionField(
                myException.getMessage(), myException.getCause(), HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(customerExceptionField, HttpStatus.NOT_FOUND);
    }

}
