package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.ownedcreatureddtos.CreateOwnedCreatureRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.CreateOwnedCreatureResponseDTO;
import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.OwnedCreature;
import com.kasintu.services.creatureservices.CreatureIDValidator;
import com.kasintu.services.inventoryservices.InventoryIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOwnedCreatureServiceImplTest {

    @Mock
    private OwnedCreatureRepository ownedCreatureRepository;

    @InjectMocks
    private CreateOwnedCreatureServiceImpl createOwnedCreatureService;

    @Mock
    private CreatureIDValidator creatureIDValidator;

    @Mock
    private InventoryIDValidator inventoryIDValidator;

    @Captor
    private ArgumentCaptor<OwnedCreature> ownedCreatureCaptor;

    @Test
    void createOwnedCreature_ReturnOwnedCreature()
    {
        OwnedCreature savedOwnedCreature = OwnedCreature.builder()
                .ownedCreatureID("1")
                .inventory(Inventory.builder().inventoryID("1").build())
                .creature(Creature.builder().creatureID("1").build())
                .build();
        when(ownedCreatureRepository.save(any())).thenReturn(savedOwnedCreature);

        CreateOwnedCreatureRequestDTO request = CreateOwnedCreatureRequestDTO.builder()
                .inventoryID("1")
                .creatureID("1")
                .build();
        CreateOwnedCreatureResponseDTO actualResponse = createOwnedCreatureService.createOwnedCreature(request);
        CreateOwnedCreatureResponseDTO expectedResponse = CreateOwnedCreatureResponseDTO.builder()
                .ownedCreatureID("1")
                .build();

        assertEquals(actualResponse, expectedResponse);
        verify(ownedCreatureRepository).save(ownedCreatureCaptor.capture());
    }
}