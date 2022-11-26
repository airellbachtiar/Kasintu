package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.services.inventoryservices.impl.GetInventoryByUserIDServiceImpl;
import com.kasintu.services.ownedcreatureservices.CreateOwnedCreatureService;
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
class SaveCreatureToInventoryFromSummonServiceImplTest {

    @Mock
    private GetInventoryByUserIDServiceImpl getInventoryService;
    @Mock
    private CreateOwnedCreatureService createOwnedCreatureService;

    @InjectMocks
    private SaveCreatureToInventoryFromSummonServiceImpl saveCreatureToInventoryFromSummonService;

    @Test
    void saveCreatureToInventory_ReturnTrue()
    {
        InventoryDTO inventory = InventoryDTO.builder()
                .ownedCreatures(List.of())
                .playerID("2")
                .inventoryID("1")
                .build();
        when(getInventoryService.getInventory("1")).thenReturn(Optional.of(inventory));

        List<CreatureDTO> creaturesList = List.of(
                CreatureDTO.builder().creatureID("1").build(),
                CreatureDTO.builder().creatureID("2").build()
        );

        UserDTO user = UserDTO.builder().userID("1").build();

        assertTrue(saveCreatureToInventoryFromSummonService.saveCreatureToInventory(creaturesList, user));
        verify(getInventoryService).getInventory("1");
    }

    @Test
    void saveCreatureToInventory_ReturnFalse()
    {
        when(getInventoryService.getInventory("1")).thenReturn(Optional.empty());

        List<CreatureDTO> creaturesList = List.of(
                CreatureDTO.builder().creatureID("1").build(),
                CreatureDTO.builder().creatureID("2").build()
        );

        UserDTO user = UserDTO.builder().userID("1").build();

        assertFalse(saveCreatureToInventoryFromSummonService.saveCreatureToInventory(creaturesList, user));
        verify(getInventoryService).getInventory("1");
    }
}