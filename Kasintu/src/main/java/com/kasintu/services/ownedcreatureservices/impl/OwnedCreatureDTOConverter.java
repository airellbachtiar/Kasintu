package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.ownedcreatureddtos.OwnedCreatureDTO;
import com.kasintu.repositories.entities.OwnedCreature;
import com.kasintu.services.creatureservices.impl.CreatureDTOConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class OwnedCreatureDTOConverter {

    public static OwnedCreatureDTO convertToDTO(OwnedCreature ownedCreature)
    {
        return OwnedCreatureDTO.builder()
                .creature(CreatureDTOConverter.convertToDTO(ownedCreature.getCreature()))
                .ownedCreatureID(ownedCreature.getOwnedCreatureID())
                .inventoryID(ownedCreature.getInventory().getInventoryID())
                .build();
    }
}
