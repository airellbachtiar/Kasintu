package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.RarityRepository;
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
class GetRarityServiceImplTest {

    @Mock
    private RarityRepository rarityRepositoryMock;

    @InjectMocks
    private GetRarityServiceImpl getRarityService;

    @Test
    void getRarityFromID_ReturnRarity()
    {
        Rarity rarity = Rarity.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
        when(rarityRepositoryMock.findById("1")).thenReturn(Optional.of(rarity));

        Optional<RarityDTO> rarityOptional = getRarityService.getRarity("1");
        RarityDTO actualResult = rarityOptional.orElseThrow();

        RarityDTO expectedResult = RarityDTO.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();

        assertEquals(expectedResult, actualResult);
        verify(rarityRepositoryMock).findById("1");
    }
}