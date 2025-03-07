package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAndErrorController {
    @ExceptionHandler(ClientSideException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(ClientSideException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflic(ConflictException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(UnauthorizedException ex) {
        return ex.getMessage();
    }
}
