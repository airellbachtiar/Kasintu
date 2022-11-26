package com.kasintu.services.creatureservices.impl;

import com.kasintu.repositories.CreatureRepository;
import com.kasintu.services.creatureservices.CreatureIDValidator;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatureIDValidatorImpl implements CreatureIDValidator {

    private final CreatureRepository creatureRepository;

    @Override
    public void validateID(String creatureID)
    {
        if(creatureID == null || !creatureRepository.existsById(creatureID))
        {
            throw new InvalidCreatureException();
        }
    }
}
