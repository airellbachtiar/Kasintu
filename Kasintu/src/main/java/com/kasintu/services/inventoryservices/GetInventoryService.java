package com.kasintu.services.inventoryservices;

import com.kasintu.dtos.inventorydtos.InventoryDTO;

import java.util.Optional;

public interface GetInventoryService {
    Optional<InventoryDTO> getInventory(String iD);
}
