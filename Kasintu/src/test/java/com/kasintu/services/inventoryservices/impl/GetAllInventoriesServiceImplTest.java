package com.kasintu.services.inventoryservices.impl;

import com.kasintu.dtos.inventorydtos.GetAllInventoriesResponseDTO;
import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.repositories.InventoryRepository;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllInventoriesServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private GetAllInventoriesServiceImpl getAllInventoriesService;

    @Test
    void getAllInventories()
    {
        List<Inventory> inventories = List.of(
                Inventory.builder()
                        .inventoryID("1")
                        .ownedCreatures(List.of())
                        .player(
                                Player.builder()
                                        .playerID("1")
                                        .user(
                                                User.builder()
                                                        .userID("1")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build(),
                Inventory.builder()
                        .inventoryID("2")
                        .ownedCreatures(List.of())
                        .player(
                                Player.builder()
                                        .playerID("2")
                                        .user(
                                                User.builder()
                                                        .userID("2")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
        when(inventoryRepository.findAll()).thenReturn(inventories);

        GetAllInventoriesResponseDTO actualResponse = getAllInventoriesService.getAllInventories();
        GetAllInventoriesResponseDTO expectedResponse = GetAllInventoriesResponseDTO.builder()
                .inventories(
                        List.of(
                                InventoryDTO.builder()
                                        .inventoryID("1")
                                        .playerID("1")
                                        .ownedCreatures(List.of())
                                        .build(),
                                InventoryDTO.builder()
                                        .inventoryID("2")
                                        .playerID("2")
                                        .ownedCreatures(List.of())
                                        .build()
                        )
                )
                .build();
        assertEquals(expectedResponse, actualResponse);
        verify(inventoryRepository).findAll();
    }
}