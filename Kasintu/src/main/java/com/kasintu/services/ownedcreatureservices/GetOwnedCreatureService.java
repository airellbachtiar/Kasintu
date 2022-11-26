package com.kasintu.services.ownedcreatureservices;

import com.kasintu.dtos.ownedcreatureddtos.OwnedCreatureDTO;

import java.util.Optional;

public interface GetOwnedCreatureService {
    Optional<OwnedCreatureDTO> getOwnedCreature(String ownedCreatureID);
}
