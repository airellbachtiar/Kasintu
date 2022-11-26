package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.ownedcreatureddtos.CreateOwnedCreatureRequestDTO;
import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.services.gameservices.SaveCreatureToInventoryService;
import com.kasintu.services.inventoryservices.impl.GetInventoryByUserIDServiceImpl;
import com.kasintu.services.ownedcreatureservices.CreateOwnedCreatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaveCreatureToInventoryFromSummonServiceImpl implements SaveCreatureToInventoryService {

    private final GetInventoryByUserIDServiceImpl getInventoryService;
    private final CreateOwnedCreatureService createOwnedCreatureService;

    @Transactional
    @Override
    public boolean saveCreatureToInventory(List<CreatureDTO> creatures, UserDTO user) {

        Optional<InventoryDTO> checkInventory = getInventoryService.getInventory(user.getUserID());
        if(checkInventory.isPresent())
        {
            InventoryDTO inventory = checkInventory.get();
            for (CreatureDTO c: creatures)
            {
                if(c != null)
                {
                    createOwnedCreatureService.createOwnedCreature(
                            CreateOwnedCreatureRequestDTO.builder()
                                    .creatureID(c.getCreatureID())
                                    .inventoryID(inventory.getInventoryID())
                                    .build()
                    );
                }
            }
            return true;
        }
        return false;
    }
}
