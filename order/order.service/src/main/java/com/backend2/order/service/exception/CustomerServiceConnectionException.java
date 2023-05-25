package com.backend2.order.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerServiceConnectionException extends RuntimeException {
    public CustomerServiceConnectionException(String message) {
        super(message);
    }
}