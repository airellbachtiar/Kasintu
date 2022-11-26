package com.kasintu.services.ownedcreatureservices;

import com.kasintu.dtos.ownedcreatureddtos.CreateOwnedCreatureRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.CreateOwnedCreatureResponseDTO;

public interface CreateOwnedCreatureService {
    CreateOwnedCreatureResponseDTO createOwnedCreature(CreateOwnedCreatureRequestDTO request);
}
