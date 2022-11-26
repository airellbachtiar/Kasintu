package com.kasintu.services.inventoryservices.impl;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetInventoryByInventoryIDServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private GetInventoryByInventoryIDServiceImpl getInventoryByInventoryIDService;

    @Test
    void getInventory()
    {
        Inventory inventory = Inventory.builder()
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
                .build();
        when(inventoryRepository.findById("1")).thenReturn(Optional.of(inventory));

        Optional<InventoryDTO> inventoryOptional = getInventoryByInventoryIDService.getInventory("1");
        InventoryDTO actualResult = inventoryOptional.orElseThrow();

        InventoryDTO expectedResponse = InventoryDTO.builder()
                .playerID("1")
                .inventoryID("1")
                .ownedCreatures(List.of())
                .build();

        assertEquals(expectedResponse, actualResult);
        verify(inventoryRepository).findById("1");
    }
}