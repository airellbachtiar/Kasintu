package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.services.rarityservices.impl.RarityDTOConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class CreatureDTOConverter {
    public static CreatureDTO convertToDTO(Creature creature)
    {
        return CreatureDTO.builder()
                .creatureID(creature.getCreatureID())
                .name(creature.getName())
                .rarity(RarityDTOConverter.convertToDTO(creature.getRarity()))
                .description(creature.getDescription())
                .build();
    }
}
