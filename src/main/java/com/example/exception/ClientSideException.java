package com.example.exception;

public class ClientSideException extends RuntimeException {
    public ClientSideException(String message) {
        super(message);
    }
}
