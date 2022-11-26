package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.ownedcreatureddtos.UpdateOwnedCreatureRequestDTO;
import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.OwnedCreature;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.inventoryservices.InventoryIDValidator;
import com.kasintu.services.ownedcreatureservices.exception.InvalidOwnedCreatureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateOwnedCreatureServiceImplTest {

    @Mock
    private OwnedCreatureRepository ownedCreatureRepositoryMock;

    @InjectMocks
    private UpdateOwnedCreatureServiceImpl updateOwnedCreatureService;

    @Mock
    private InventoryIDValidator inventoryIDValidator;

    @Test
    void updateOwnedCreature_Successful()
    {
        OwnedCreature ownedCreatureBeforeUpdate = OwnedCreature.builder()
                .ownedCreatureID("1")
                .creature(Creature.builder()
                        .creatureID("1")
                        .rarity(
                                Rarity.builder()
                                        .rarityID("1")
                                        .build()
                        )
                        .build())
                .inventory(Inventory.builder()
                        .inventoryID("1")
                        .build())
                .build();
        when(ownedCreatureRepositoryMock.findById("1")).thenReturn(Optional.of(ownedCreatureBeforeUpdate));

        UpdateOwnedCreatureRequestDTO request = UpdateOwnedCreatureRequestDTO.builder()
                .ownedCreatureID("1")
                .inventoryID("2")
                .build();
        updateOwnedCreatureService.updateOwnedCreature(request);

        OwnedCreature expectedSavedOwnedCreature = OwnedCreature.builder()
                .ownedCreatureID("1")
                .creature(Creature.builder()
                        .creatureID("1")
                        .rarity(
                                Rarity.builder()
                                        .rarityID("1")
                                        .build()
                        )
                        .build())
                .inventory(Inventory.builder()
                        .inventoryID("2")
                        .build())
                .build();
        verify(ownedCreatureRepositoryMock).findById("1");
        verify(ownedCreatureRepositoryMock).save(expectedSavedOwnedCreature);
    }

    @Test
    void updateOwnedCreature_OwnedCreatureNotFound()
    {
        when(ownedCreatureRepositoryMock.findById("1")).thenReturn(Optional.empty());

        UpdateOwnedCreatureRequestDTO request = UpdateOwnedCreatureRequestDTO.builder()
                .ownedCreatureID("1")
                .inventoryID("2")
                .build();

        assertThrows(InvalidOwnedCreatureException.class, ()-> updateOwnedCreatureService.updateOwnedCreature(request));
        verify(ownedCreatureRepositoryMock).findById("1");
        verifyNoInteractions(inventoryIDValidator);
    }
}