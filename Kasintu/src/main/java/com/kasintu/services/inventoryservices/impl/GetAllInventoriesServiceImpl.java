package com.kasintu.services.inventoryservices.impl;

import com.kasintu.dtos.inventorydtos.GetAllInventoriesResponseDTO;
import com.kasintu.repositories.InventoryRepository;
import com.kasintu.services.inventoryservices.GetAllInventoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllInventoriesServiceImpl implements GetAllInventoriesService
{

    private final InventoryRepository inventoryRepository;

    @Override
    public GetAllInventoriesResponseDTO getAllInventories() {
        return GetAllInventoriesResponseDTO.builder()
                .inventories(inventoryRepository.findAll().stream().map(InventoryDTOConverter::convertToDTO).toList())
                .build();
    }
}
