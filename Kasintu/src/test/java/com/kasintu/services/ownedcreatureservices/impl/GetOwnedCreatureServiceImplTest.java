package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.ownedcreatureddtos.OwnedCreatureDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.OwnedCreature;
import com.kasintu.repositories.entities.Rarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOwnedCreatureServiceImplTest {

    @Mock
    private OwnedCreatureRepository ownedCreatureRepositoryMock;

    @InjectMocks
    private GetOwnedCreatureServiceImpl getOwnedCreatureService;

    @Test
    void getOwnedCreature()
    {
        OwnedCreature ownedCreature = OwnedCreature.builder()
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
        when(ownedCreatureRepositoryMock.findById("1")).thenReturn(Optional.of(ownedCreature));

        Optional<OwnedCreatureDTO> optionalOwnedCreature = getOwnedCreatureService.getOwnedCreature("1");
        OwnedCreatureDTO actualResult = optionalOwnedCreature.orElseThrow();

        OwnedCreatureDTO expectedResult = OwnedCreatureDTO.builder()
                .ownedCreatureID("1")
                .creature(CreatureDTO.builder()
                        .creatureID("1")
                        .rarity(
                                RarityDTO.builder()
                                        .rarityID("1")
                                        .build()
                        )
                        .build())
                .inventoryID("1")
                .build();
        assertEquals(expectedResult, actualResult);
        verify(ownedCreatureRepositoryMock).findById("1");
    }
}