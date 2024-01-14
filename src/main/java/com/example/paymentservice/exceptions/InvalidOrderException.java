package com.example.paymentservice.exceptions;

public class InvalidOrderException extends Exception{
    public InvalidOrderException(String message) {
        super(message);
    }
}
