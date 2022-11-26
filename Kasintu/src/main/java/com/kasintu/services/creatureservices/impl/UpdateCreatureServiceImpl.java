package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.UpdateCreatureRequestDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.creatureservices.UpdateCreatureService;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import com.kasintu.services.rarityservices.RarityIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCreatureServiceImpl implements UpdateCreatureService {

    private final CreatureRepository creatureRepository;
    private final RarityIDValidator rarityIDValidator;

    @Transactional
    @Override
    public void updateCreature(UpdateCreatureRequestDTO request) {
        Optional<Creature> creature = creatureRepository.findById(request.getCreatureID());
        if(creature.isEmpty())
        {
            throw new InvalidCreatureException();
        }
        Creature updateCreature = creature.get();

        if(!Objects.equals(updateCreature.getName(), request.getName()) && creatureRepository.existsByName(request.getName()))
        {
            throw new InvalidCreatureException("CREATURE_NAME_EXIST");
        }

        rarityIDValidator.validateID(request.getRarityID());

        updateCreature.setName(request.getName());
        updateCreature.setRarity(Rarity.builder().rarityID(request.getRarityID()).build());
        updateCreature.setDescription(request.getDescription());

        creatureRepository.save(updateCreature);
    }
}
