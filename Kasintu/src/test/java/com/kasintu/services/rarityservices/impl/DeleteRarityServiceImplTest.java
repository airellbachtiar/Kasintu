package com.kasintu.services.rarityservices.impl;

import com.kasintu.repositories.RarityRepository;
import com.kasintu.services.rarityservices.exception.InvalidRarityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteRarityServiceImplTest {

    @Mock
    private RarityRepository rarityRepositoryMock;

    @InjectMocks
    private DeleteRarityServiceImpl deleteRarityService;

    @Test
    void deleteRarity_Successful()
    {
        String rarityID = "1";

        when(rarityRepositoryMock.existsById(rarityID)).thenReturn(true);
        deleteRarityService.deleteRarity(rarityID);

        verify(rarityRepositoryMock).existsById(rarityID);
        verify(rarityRepositoryMock).deleteById(rarityID);
    }

    @Test
    void deleteRarity_IDNotFound_ThrowException()
    {
        String rarityID = "1";

        when(rarityRepositoryMock.existsById(rarityID)).thenReturn(false);

        assertThrows(InvalidRarityException.class, ()->deleteRarityService.deleteRarity(rarityID));
    }
}