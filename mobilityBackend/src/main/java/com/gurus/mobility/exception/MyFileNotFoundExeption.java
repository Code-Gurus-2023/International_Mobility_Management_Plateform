package com.gurus.mobility.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundExeption extends RuntimeException{
    public MyFileNotFoundExeption(String message) {
        super(message);
    }
    public MyFileNotFoundExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
