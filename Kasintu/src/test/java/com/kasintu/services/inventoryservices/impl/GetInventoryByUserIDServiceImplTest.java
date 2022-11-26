package com.kasintu.services.inventoryservices.impl;

import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.repositories.InventoryRepository;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.playerservices.GetPlayerService;
import com.kasintu.services.playerservices.PlayerIDValidator;
import com.kasintu.services.playerservices.exception.InvalidPlayerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetInventoryByUserIDServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private PlayerIDValidator playerIDValidator;
    @Mock
    private GetPlayerService getPlayerService;

    @InjectMocks
    private GetInventoryByUserIDServiceImpl getInventoryByUserIDService;

    @Test
    void getInventory_Successful_ReturnInventory()
    {
        PlayerDTO player = PlayerDTO.builder()
                .playerID("1")
                .userID("1")
                .build();
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
        when(getPlayerService.getPlayer("1")).thenReturn(Optional.of(player));
        when(inventoryRepository.findByPlayer_PlayerID("1")).thenReturn(Optional.of(inventory));

        Optional<InventoryDTO> inventoryOptional = getInventoryByUserIDService.getInventory("1");
        InventoryDTO actualResult = inventoryOptional.orElseThrow();

        InventoryDTO expectedResponse = InventoryDTO.builder()
                .playerID("1")
                .inventoryID("1")
                .ownedCreatures(List.of())
                .build();

        assertEquals(expectedResponse, actualResult);
        verify(inventoryRepository).findByPlayer_PlayerID("1");
    }

    @Test
    void getInventory_PlayerNotFound_ThrowException()
    {
        when(getPlayerService.getPlayer("1")).thenReturn(Optional.empty());

        assertThrows(InvalidPlayerException.class, ()->getInventoryByUserIDService.getInventory("1"));
        verifyNoInteractions(inventoryRepository);
        verifyNoInteractions(playerIDValidator);
    }
}