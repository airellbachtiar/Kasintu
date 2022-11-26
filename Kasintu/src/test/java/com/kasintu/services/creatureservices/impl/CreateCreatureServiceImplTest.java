package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreateCreatureRequestDTO;
import com.kasintu.dtos.creaturedtos.CreateCreatureResponseDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import com.kasintu.services.rarityservices.RarityIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCreatureServiceImplTest {

    @Mock
    private CreatureRepository creatureRepositoryMock;

    @InjectMocks
    private CreateCreatureServiceImpl createCreatureService;

    @Mock
    private RarityIDValidator rarityIDValidatorMock;

    @Captor
    private ArgumentCaptor<Creature> creatureCaptor;

    @Test
    void createCreature_ReturnNewCreature()
    {
        Creature savedCreature = Creature.builder()
                .creatureID("1")
                .name("Creature")
                .rarity(Rarity.builder().rarityID("1e2473bbf867434a9f78e1a22286f0cd").build())
                .description("")
                .build();
        when(creatureRepositoryMock.save(any())).thenReturn(savedCreature);

        CreateCreatureRequestDTO request = CreateCreatureRequestDTO.builder()
                .name("Creature")
                .rarityID("1e2473bbf867434a9f78e1a22286f0cd")
                .description("")
                .build();
        CreateCreatureResponseDTO actualResponse = createCreatureService.createCreature(request);
        CreateCreatureResponseDTO expectedResponse = CreateCreatureResponseDTO.builder().creatureID("1").build();

        assertThat(actualResponse).usingRecursiveComparison().ignoringFields("creatureID").isEqualTo(expectedResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(creatureRepositoryMock).save(creatureCaptor.capture());
    }

    @Test
    void createCreature_NameAlreadyExist_ThrowException()
    {
        String name = "Creature";
        when(creatureRepositoryMock.existsByName(name)).thenReturn(true);

        CreateCreatureRequestDTO request = CreateCreatureRequestDTO.builder().name(name).build();
        assertThrows(InvalidCreatureException.class, ()-> createCreatureService.createCreature(request));

        verify(creatureRepositoryMock).existsByName(name);
        verifyNoInteractions(rarityIDValidatorMock);
    }

    @Test
    void createCreature_NameDoesNotExist_ThrowException()
    {
        String name = "";

        CreateCreatureRequestDTO request = CreateCreatureRequestDTO.builder().name(name).build();
        assertThrows(InvalidCreatureException.class, ()-> createCreatureService.createCreature(request));

        verifyNoInteractions(rarityIDValidatorMock);
    }
}