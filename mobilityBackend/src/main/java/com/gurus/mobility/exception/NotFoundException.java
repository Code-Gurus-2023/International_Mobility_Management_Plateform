package com.gurus.mobility.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException {

    public NotFoundException(String msg) {
        super();

    }


}