package com.example.paymentservice.controllers;//package com.example.paymentservice.controllers;

import com.example.paymentservice.exceptions.InvalidOrderException;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler({StripeException.class})
    public ResponseEntity<String> handleException(StripeException e) {

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({InvalidOrderException.class})
    public ResponseEntity<String> handleException(InvalidOrderException e) {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
