package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesResponseDTO;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllOwnedCreaturesServiceImplTest {

    @Mock
    private OwnedCreatureRepository ownedCreatureRepository;

    @InjectMocks
    private GetAllOwnedCreaturesServiceImpl getAllOwnedCreaturesService;

    @Test
    void getAllOwnedCreatures_ReturnOwnedCreatures()
    {
        List<OwnedCreature> ownedCreatures = List.of(
                OwnedCreature.builder()
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
                        .build(),
                OwnedCreature.builder()
                        .ownedCreatureID("2")
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
                        .build()
        );
        when(ownedCreatureRepository.findAll()).thenReturn(ownedCreatures);

        GetAllOwnedCreaturesResponseDTO actualResponse = getAllOwnedCreaturesService.getAllOwnedCreatures(GetAllOwnedCreaturesRequestDTO.builder().build());
        GetAllOwnedCreaturesResponseDTO expectedResponse = GetAllOwnedCreaturesResponseDTO.builder()
                .ownedCreatures(
                        List.of(
                                OwnedCreatureDTO.builder()
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
                                        .build(),
                                OwnedCreatureDTO.builder()
                                        .ownedCreatureID("2")
                                        .creature(CreatureDTO.builder()
                                                .creatureID("1")
                                                .rarity(
                                                        RarityDTO.builder()
                                                                .rarityID("1")
                                                                .build()
                                                )
                                                .build())
                                        .inventoryID("1")
                                        .build()
                        )
                )
                .build();
        assertEquals(expectedResponse, actualResponse);
        verify(ownedCreatureRepository).findAll();
    }

    @Test
    void getAllOwnedCreatures_FilterInventoryID_ReturnOwnedCreaturesWithSpecificInventory()
    {
        List<OwnedCreature> ownedCreatures = List.of(
                OwnedCreature.builder()
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
                        .build()
        );
        when(ownedCreatureRepository.findAllByInventory_InventoryID("1")).thenReturn(ownedCreatures);

        GetAllOwnedCreaturesResponseDTO actualResponse = getAllOwnedCreaturesService.getAllOwnedCreatures(
                GetAllOwnedCreaturesRequestDTO.builder()
                        .inventoryID("1")
                        .build()
        );

        GetAllOwnedCreaturesResponseDTO expectedResponse = GetAllOwnedCreaturesResponseDTO.builder()
                .ownedCreatures(
                        List.of(
                                OwnedCreatureDTO.builder()
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
                                        .build()
                        )
                ).build();
        assertEquals(expectedResponse, actualResponse);
        verify(ownedCreatureRepository).findAllByInventory_InventoryID("1");
    }
}