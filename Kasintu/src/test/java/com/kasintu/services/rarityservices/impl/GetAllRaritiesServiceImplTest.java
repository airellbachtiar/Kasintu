package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.GetAllRaritiesResponseDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.RarityRepository;
import com.kasintu.repositories.entities.Rarity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllRaritiesServiceImplTest {

    @Mock
    private RarityRepository rarityRepositoryMock;

    @InjectMocks
    private GetAllRaritiesServiceImpl getAllRaritiesService;

    @Test
    void getAllRarities_ReturnRarities()
    {
        List<Rarity> rarities = List.of(
                Rarity.builder().rarityID("common").rarityType("common").build(),
                Rarity.builder().rarityID("uncommon").rarityType("uncommon").build()
        );
        when(rarityRepositoryMock.findAll()).thenReturn(rarities);

        GetAllRaritiesResponseDTO actualResult = getAllRaritiesService.getAllRarities();

        GetAllRaritiesResponseDTO expectedResult = GetAllRaritiesResponseDTO.builder()
                .rarities(
                        List.of(RarityDTO.builder().rarityID("common").rarityType("common").build(),
                                RarityDTO.builder().rarityID("uncommon").rarityType("uncommon").build()))
                .build();

        assertEquals(expectedResult.getRarities().size(), actualResult.getRarities().size());

        verify(rarityRepositoryMock).findAll();
    }
}