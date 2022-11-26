package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.entities.Rarity;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class RarityDTOConverter {

    public static RarityDTO convertToDTO(Rarity rarity)
    {
        return RarityDTO.builder().rarityID(rarity.getRarityID()).rarityType(rarity.getRarityType()).rarityLevel(rarity.getRarityLevel()).build();
    }
}
