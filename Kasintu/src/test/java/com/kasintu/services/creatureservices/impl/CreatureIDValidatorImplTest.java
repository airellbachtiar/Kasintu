package com.kasintu.services.creatureservices.impl;

import com.kasintu.repositories.CreatureRepository;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatureIDValidatorImplTest {

    @Mock
    private CreatureRepository creatureRepositoryMock;

    @InjectMocks
    private CreatureIDValidatorImpl creatureIDValidator;

    @Test
    void validateID_ThrowException_IDNotFound()
    {
        String creatureID = "1";

        when(creatureRepositoryMock.existsById(creatureID)).thenReturn(false);
        assertThrows(InvalidCreatureException.class, ()-> creatureIDValidator.validateID(creatureID));
    }

    @Test
    void validateID_ThrowException_IDIsNull()
    {
        assertThrows(InvalidCreatureException.class, ()-> creatureIDValidator.validateID(null));
    }

    @Test
    void validateID_Successful()
    {
        String creatureID = "1";

        when(creatureRepositoryMock.existsById(creatureID)).thenReturn(true);
        creatureIDValidator.validateID(creatureID);

        verify(creatureRepositoryMock).existsById(creatureID);
    }
}