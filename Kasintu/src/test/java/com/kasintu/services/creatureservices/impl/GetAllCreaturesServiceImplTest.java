package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.creaturedtos.GetAllCreaturesRequestDTO;
import com.kasintu.dtos.creaturedtos.GetAllCreaturesResponseDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Rarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllCreaturesServiceImplTest {

    @Mock
    private CreatureRepository creatureRepositoryMock;

    @InjectMocks
    private GetAllCreaturesServiceImpl getAllCreaturesServiceImpl;

    @Test
    void getAllCreatures_ReturnCreatures()
    {
        List<Creature> creatures = List.of(
                Creature.builder().creatureID("1").rarity(Rarity.builder().rarityID("common").build()).name("Creature1").build(),
                Creature.builder().creatureID("2").rarity(Rarity.builder().rarityID("common").build()).name("Creature2").build()
        );
        when(creatureRepositoryMock.findAll()).thenReturn(creatures);

        GetAllCreaturesResponseDTO actualResponse = getAllCreaturesServiceImpl.getAllCreatures(GetAllCreaturesRequestDTO.builder().build());

        GetAllCreaturesResponseDTO expectedResponse = GetAllCreaturesResponseDTO.builder()
                .creatures(
                        List.of(
                                CreatureDTO.builder().creatureID("1").rarity(RarityDTO.builder().rarityID("common").build()).name("Creature1").build(),
                                CreatureDTO.builder().creatureID("2").rarity(RarityDTO.builder().rarityID("common").build()).name("Creature2").build()
                        )
                ).build();

        assertEquals(expectedResponse, actualResponse);

        verify(creatureRepositoryMock).findAll();
    }

    @Test
    void getAllCreatures_OnlyRarity_ReturnCreaturesWithSpecificRarity()
    {
        Rarity commonRarity = Rarity.builder().rarityID("common").rarityType("common").build();
        List<Creature> creatures = List.of(
                Creature.builder().creatureID("1").name("Creature1").rarity(commonRarity).build()
        );
        when(creatureRepositoryMock.findAllByRarity_RarityIDOrderByName("common")).thenReturn(creatures);

        GetAllCreaturesResponseDTO actualResponse = getAllCreaturesServiceImpl.getAllCreatures(GetAllCreaturesRequestDTO.builder().rarityID("common").build());

        RarityDTO commonRarityDTO = RarityDTO.builder().rarityID("common").rarityType("common").build();
        GetAllCreaturesResponseDTO expectedResponse = GetAllCreaturesResponseDTO.builder()
                .creatures(
                        List.of(
                                CreatureDTO.builder().creatureID("1").name("Creature1").rarity(commonRarityDTO).build()
                        )
                ).build();

        assertEquals(expectedResponse, actualResponse);

        verify(creatureRepositoryMock).findAllByRarity_RarityIDOrderByName(any());
    }

}