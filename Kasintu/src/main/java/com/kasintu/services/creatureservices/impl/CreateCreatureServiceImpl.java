package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreateCreatureRequestDTO;
import com.kasintu.dtos.creaturedtos.CreateCreatureResponseDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.creatureservices.CreateCreatureService;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import com.kasintu.services.rarityservices.RarityIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCreatureServiceImpl implements CreateCreatureService {

    private final CreatureRepository creatureRepository;
    private final RarityIDValidator rarityIDValidator;

    @Transactional
    @Override
    public CreateCreatureResponseDTO createCreature(CreateCreatureRequestDTO request) {

        if(!StringUtils.hasText(request.getName()) || creatureRepository.existsByName(request.getName()))
        {
            throw new InvalidCreatureException();
        }

        rarityIDValidator.validateID(request.getRarityID());

        String creatureID = UUID.randomUUID().toString().replace("-", "");

        Creature newCreature = Creature.builder()
                .creatureID(creatureID)
                .rarity(Rarity.builder().rarityID(request.getRarityID()).build())
                .name(request.getName())
                .description(request.getDescription())
                .build();
        Creature savedCreature = creatureRepository.save(newCreature);

        return CreateCreatureResponseDTO.builder().creatureID(savedCreature.getCreatureID()).build();
    }
}
