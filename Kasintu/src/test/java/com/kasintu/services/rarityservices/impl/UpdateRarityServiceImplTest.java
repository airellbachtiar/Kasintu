package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.UpdateRarityRequestDTO;
import com.kasintu.repositories.RarityRepository;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.rarityservices.exception.InvalidRarityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateRarityServiceImplTest {

    @Mock
    private RarityRepository rarityRepositoryMock;

    @InjectMocks
    private UpdateRarityServiceImpl updateRarityService;

    @Test
    void updateCreature_Successful() {
        Rarity rarityBeforeUpdate = Rarity.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
        when(rarityRepositoryMock.findById("1")).thenReturn(Optional.of(rarityBeforeUpdate));

        UpdateRarityRequestDTO request = UpdateRarityRequestDTO.builder()
                .rarityID("1")
                .rarityType("Uncommon")
                .rarityLevel(1)
                .build();
        when(rarityRepositoryMock.existsByRarityType("Uncommon")).thenReturn(false);
        updateRarityService.updateRarity(request);

        Rarity expectedSavedRarity = Rarity.builder()
                        .rarityID("1")
                        .rarityType("Uncommon")
                        .rarityLevel(1)
                        .build();

        verify(rarityRepositoryMock).findById("1");
        verify(rarityRepositoryMock).existsByRarityType("Uncommon");
        verify(rarityRepositoryMock).save(expectedSavedRarity);
    }

    @Test
    void updateRarity_RarityNotFound_ThrowException()
    {
        when(rarityRepositoryMock.findById("1")).thenReturn(Optional.empty());

        UpdateRarityRequestDTO request = UpdateRarityRequestDTO.builder()
                .rarityID("1")
                .rarityType("Uncommon")
                .rarityLevel(1)
                .build();
        assertThrows(InvalidRarityException.class, () -> updateRarityService.updateRarity(request));

        verify(rarityRepositoryMock).findById("1");
    }

    @Test
    void updateRarity_TypeAlreadyExist_ThrowException()
    {
        Rarity rarityBeforeUpdate = Rarity.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
        when(rarityRepositoryMock.findById("1")).thenReturn(Optional.of((rarityBeforeUpdate)));

        UpdateRarityRequestDTO request = UpdateRarityRequestDTO.builder()
                .rarityID("1")
                .rarityType("Uncommon")
                .rarityLevel(1)
                .build();
        when(rarityRepositoryMock.existsByRarityType("Uncommon")).thenReturn(true);
        assertThrows(InvalidRarityException.class, () -> updateRarityService.updateRarity(request));
        verify(rarityRepositoryMock).findById("1");
        verify(rarityRepositoryMock).existsByRarityType("Uncommon");
    }
}