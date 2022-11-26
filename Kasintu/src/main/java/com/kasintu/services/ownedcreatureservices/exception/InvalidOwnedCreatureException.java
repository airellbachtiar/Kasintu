package com.kasintu.services.ownedcreatureservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidOwnedCreatureException extends ResponseStatusException
{
    public InvalidOwnedCreatureException()
    {
        super(HttpStatus.BAD_REQUEST, "OWNED_CREATURE_INVALID");
    }
}
