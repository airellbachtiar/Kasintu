package com.kasintu.services.inventoryservices.impl;

import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.repositories.InventoryRepository;
import com.kasintu.services.inventoryservices.GetInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetInventoryByInventoryIDServiceImpl implements GetInventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Optional<InventoryDTO> getInventory(String inventoryID) {

        return inventoryRepository.findById(inventoryID).map(InventoryDTOConverter::convertToDTO);
    }
}
