package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.ownedcreatureddtos.CreateOwnedCreatureRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.CreateOwnedCreatureResponseDTO;
import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.OwnedCreature;
import com.kasintu.services.creatureservices.CreatureIDValidator;
import com.kasintu.services.inventoryservices.InventoryIDValidator;
import com.kasintu.services.ownedcreatureservices.CreateOwnedCreatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateOwnedCreatureServiceImpl implements CreateOwnedCreatureService
{

    private final OwnedCreatureRepository ownedCreatureRepository;
    private final CreatureIDValidator creatureIDValidator;
    private final InventoryIDValidator inventoryIDValidator;

    @Override
    public CreateOwnedCreatureResponseDTO createOwnedCreature(CreateOwnedCreatureRequestDTO request)
    {
        creatureIDValidator.validateID(request.getCreatureID());
        inventoryIDValidator.validateID(request.getInventoryID());

        String ownedCreatureID = UUID.randomUUID().toString().replace("-", "");

        OwnedCreature newOwnedCreature = OwnedCreature.builder()
                .ownedCreatureID(ownedCreatureID)
                .creature(Creature.builder().creatureID(request.getCreatureID()).build())
                .inventory(Inventory.builder().inventoryID(request.getInventoryID()).build())
                .build();

        OwnedCreature savedOwnedCreature = ownedCreatureRepository.save(newOwnedCreature);

        return CreateOwnedCreatureResponseDTO.builder().ownedCreatureID(savedOwnedCreature.getOwnedCreatureID()).build();
    }
}
