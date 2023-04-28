package com.upgrad.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PaymentExceptionHandler {
    public PaymentExceptionHandler() {
    }

    @ExceptionHandler({PaymentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> processValidationError(PaymentException ex){
        return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
    }
}
