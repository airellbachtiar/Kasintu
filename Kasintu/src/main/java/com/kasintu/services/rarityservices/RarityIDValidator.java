package com.kasintu.services.rarityservices;

import com.kasintu.services.rarityservices.exception.InvalidRarityException;

public interface RarityIDValidator {
    void validateID(String rarityID) throws InvalidRarityException;
}
