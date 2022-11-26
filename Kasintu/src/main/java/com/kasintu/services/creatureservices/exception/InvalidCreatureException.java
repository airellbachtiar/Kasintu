package com.kasintu.services.creatureservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCreatureException extends ResponseStatusException
{
    public InvalidCreatureException()
    {
        super(HttpStatus.BAD_REQUEST, "CREATURE_INVALID");
    }

    public InvalidCreatureException(String errorCode)
    {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
