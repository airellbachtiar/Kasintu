package com.kasintu.services.playerservices.impl;

import com.kasintu.repositories.PlayerRepository;
import com.kasintu.services.playerservices.PlayerIDValidator;
import com.kasintu.services.playerservices.exception.InvalidPlayerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerIDValidatorImpl implements PlayerIDValidator
{

    private final PlayerRepository playerRepository;

    @Override
    public void validateID(String playerID)
    {
        if(playerID == null || !playerRepository.existsById(playerID))
        {
            throw new InvalidPlayerException();
        }
    }
}
