package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.ownedcreatureddtos.UpdateOwnedCreatureRequestDTO;
import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.OwnedCreature;
import com.kasintu.services.inventoryservices.InventoryIDValidator;
import com.kasintu.services.ownedcreatureservices.UpdateOwnedCreatureService;
import com.kasintu.services.ownedcreatureservices.exception.InvalidOwnedCreatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateOwnedCreatureServiceImpl implements UpdateOwnedCreatureService
{

    private final OwnedCreatureRepository ownedCreatureRepository;
    private final InventoryIDValidator inventoryIDValidator;

    @Override
    @Transactional
    public void updateOwnedCreature(UpdateOwnedCreatureRequestDTO request)
    {
        Optional<OwnedCreature> ownedCreature = ownedCreatureRepository.findById(request.getOwnedCreatureID());
        if(ownedCreature.isEmpty())
        {
            throw new InvalidOwnedCreatureException();
        }
        OwnedCreature updateOwnedCreature = ownedCreature.get();

        inventoryIDValidator.validateID(request.getInventoryID());

        updateOwnedCreature.setInventory(Inventory.builder().inventoryID(request.getInventoryID()).build());

        ownedCreatureRepository.save(updateOwnedCreature);
    }
}
