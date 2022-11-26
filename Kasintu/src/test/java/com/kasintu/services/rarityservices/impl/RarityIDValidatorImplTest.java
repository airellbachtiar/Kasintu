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
class RarityIDValidatorImplTest {

    @Mock
    private RarityRepository rarityRepositoryMock;

    @InjectMocks
    private RarityIDValidatorImpl rarityIDValidator;

    @Test
    void validateID_ThrowException_IDNotFound()
    {
        String rarityID = "1";

        when(rarityRepositoryMock.existsById(rarityID)).thenReturn(false);
        assertThrows(InvalidRarityException.class, ()-> rarityIDValidator.validateID(rarityID));
    }

    @Test
    void validateID_ThrowException_IDIsNull()
    {
        assertThrows(InvalidRarityException.class, ()-> rarityIDValidator.validateID(null));
    }

    @Test
    void validateID_Successful()
    {
        String rarityID = "1";

        when(rarityRepositoryMock.existsById(rarityID)).thenReturn(true);
        rarityIDValidator.validateID(rarityID);

        verify(rarityRepositoryMock).existsById(rarityID);
    }
}