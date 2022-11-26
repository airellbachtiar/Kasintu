package com.kasintu.services.creatureservices;

import com.kasintu.dtos.creaturedtos.CreateCreatureRequestDTO;
import com.kasintu.dtos.creaturedtos.CreateCreatureResponseDTO;

public interface CreateCreatureService {
    CreateCreatureResponseDTO createCreature(CreateCreatureRequestDTO request);
}
