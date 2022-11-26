package com.kasintu.services.playerservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPlayerException extends ResponseStatusException
{
    public InvalidPlayerException()
    {
        super(HttpStatus.BAD_REQUEST, "PLAYER_INVALID");
    }
}
