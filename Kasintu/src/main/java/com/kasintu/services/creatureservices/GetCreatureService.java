package com.kasintu.services.creatureservices;

import com.kasintu.dtos.creaturedtos.CreatureDTO;

import java.util.Optional;

public interface GetCreatureService {
    Optional<CreatureDTO> getCreature(String creatureID);
}
