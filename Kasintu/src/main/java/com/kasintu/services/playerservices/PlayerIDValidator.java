package com.kasintu.services.playerservices;

import com.kasintu.services.playerservices.exception.InvalidPlayerException;

public interface PlayerIDValidator
{
    void validateID(String playerID) throws InvalidPlayerException;
}
