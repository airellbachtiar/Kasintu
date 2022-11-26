package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.repositories.entities.Creature;
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
class GetCreatureServiceImplTest {

    @Mock
    private CreatureRepository creatureRepositoryMock;

    @InjectMocks
    private GetCreatureServiceImpl getCreatureServiceImpl;


    @Test
    void getCreatureFromID_ReturnCreature()
    {
        Creature creature = Creature.builder()
                .creatureID("1")
                .name("Creature")
                .rarity(Rarity.builder().rarityID("common").build())
                .description("")
                .build();
        when(creatureRepositoryMock.findById("1")).thenReturn(Optional.of(creature));

        Optional<CreatureDTO> creatureOptional = getCreatureServiceImpl.getCreature("1");
        CreatureDTO actualResult = creatureOptional.orElseThrow();

        CreatureDTO expectedResult = CreatureDTO.builder()
                .creatureID("1")
                .name("Creature")
                .rarity(RarityDTO.builder().rarityID("common").build())
                .description("")
                .build();

        assertEquals(expectedResult, actualResult);
        verify(creatureRepositoryMock).findById("1");
    }
}