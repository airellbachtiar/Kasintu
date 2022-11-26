package com.kasintu.services.loginservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAccessTokenException extends ResponseStatusException {
    public InvalidAccessTokenException(String error) {
        super(HttpStatus.UNAUTHORIZED, error);
    }
}
