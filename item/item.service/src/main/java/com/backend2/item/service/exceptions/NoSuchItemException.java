package com.backend2.item.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchItemException extends RuntimeException{
    public NoSuchItemException(String message) {
        super(message);
    }
}

