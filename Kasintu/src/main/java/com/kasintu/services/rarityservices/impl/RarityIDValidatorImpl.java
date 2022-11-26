package com.kasintu.services.rarityservices.impl;

import com.kasintu.repositories.RarityRepository;
import com.kasintu.services.rarityservices.RarityIDValidator;
import com.kasintu.services.rarityservices.exception.InvalidRarityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RarityIDValidatorImpl implements RarityIDValidator {

    private final RarityRepository rarityRepository;

    @Override
    public void validateID(String rarityID)
    {
        if(rarityID == null || !rarityRepository.existsById(rarityID))
        {
            throw new InvalidRarityException();
        }
    }
}
