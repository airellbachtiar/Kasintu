package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.CreateRarityRequestDTO;
import com.kasintu.dtos.raritydtos.CreateRarityResponseDTO;
import com.kasintu.repositories.RarityRepository;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.rarityservices.exception.InvalidRarityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateRarityServiceImplTest {

    @Mock
    private RarityRepository rarityRepositoryMock;

    @InjectMocks
    private CreateRarityServiceImpl createRarityService;

    @Captor
    private ArgumentCaptor<Rarity> rarityCaptor;

    @Test
    void createRarity_ReturnNewRarity()
    {
        Rarity savedRarity = Rarity.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(1)
                .build();
        when(rarityRepositoryMock.save(any())).thenReturn(savedRarity);

        CreateRarityRequestDTO request = CreateRarityRequestDTO.builder()
                .rarityType("Common")
                .rarityLevel(1)
                .build();

        CreateRarityResponseDTO actualResponse = createRarityService.createRarity(request);
        CreateRarityResponseDTO expectedResponse = CreateRarityResponseDTO.builder()
                .rarityID("1")
                .build();

        assertEquals(actualResponse, expectedResponse);
        verify(rarityRepositoryMock).save(rarityCaptor.capture());
    }

    @Test
    void createRarity_TypeAlreadyExist_ThrowException()
    {
        String type = "Common";
        when(rarityRepositoryMock.existsByRarityType(type)).thenReturn(true);

        CreateRarityRequestDTO request = CreateRarityRequestDTO.builder()
                .rarityType(type)
                .rarityLevel(1)
                .build();

        assertThrows(InvalidRarityException.class, () -> createRarityService.createRarity(request));

        verify(rarityRepositoryMock).existsByRarityType(type);
    }

    @Test
    void createRarity_TypeIsNull_ThrowException()
    {
        CreateRarityRequestDTO request = CreateRarityRequestDTO.builder()
                .rarityType("")
                .rarityLevel(1)
                .build();

        assertThrows(InvalidRarityException.class, () -> createRarityService.createRarity(request));
    }
}