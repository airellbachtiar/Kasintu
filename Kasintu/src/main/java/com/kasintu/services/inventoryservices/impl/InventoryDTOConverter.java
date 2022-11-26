package com.kasintu.services.inventoryservices.impl;

import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.services.ownedcreatureservices.impl.OwnedCreatureDTOConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
final class InventoryDTOConverter
{

    public static InventoryDTO convertToDTO(Inventory inventory)
    {
        return InventoryDTO.builder()
                .playerID(inventory.getPlayer().getPlayerID())
                .inventoryID(inventory.getInventoryID())
                .ownedCreatures(inventory.getOwnedCreatures().stream().map(OwnedCreatureDTOConverter::convertToDTO).toList())
                .build();
    }
}
