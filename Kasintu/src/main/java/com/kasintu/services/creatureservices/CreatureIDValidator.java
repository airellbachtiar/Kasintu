package com.kasintu.services.creatureservices;

import com.kasintu.services.creatureservices.exception.InvalidCreatureException;

public interface CreatureIDValidator {
    void validateID(String creatureID) throws InvalidCreatureException;
}
