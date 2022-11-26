package com.kasintu.services.rarityservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRarityException extends ResponseStatusException {

    public InvalidRarityException()
    {
        super(HttpStatus.BAD_REQUEST, "RARITY_INVALID");
    }

    public InvalidRarityException(String errorCode)
    {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
