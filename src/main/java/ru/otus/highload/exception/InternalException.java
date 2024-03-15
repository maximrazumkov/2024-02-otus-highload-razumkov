package ru.otus.highload.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {
    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }
}
