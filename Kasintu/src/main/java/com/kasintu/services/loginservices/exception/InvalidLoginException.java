package com.kasintu.services.loginservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidLoginException extends ResponseStatusException {

    public InvalidLoginException()
    {
        super(HttpStatus.BAD_REQUEST, "CREDENTIALS_INVALID");
    }

    public InvalidLoginException(String errorCode)
    {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
