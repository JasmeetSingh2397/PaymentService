package com.example.paymentservice.controllers;//package com.example.paymentservice.controllers;
//
//import com.stripe.exception.StripeException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ExceptionAdvices {
//    @ExceptionHandler({StripeException.class})
//    public ResponseEntity<String> handleException(Exception e) {
//
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
