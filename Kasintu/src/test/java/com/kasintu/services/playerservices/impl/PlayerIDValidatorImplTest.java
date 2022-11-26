package com.kasintu.services.playerservices.impl;

import com.kasintu.repositories.PlayerRepository;
import com.kasintu.services.playerservices.exception.InvalidPlayerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerIDValidatorImplTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @InjectMocks
    private PlayerIDValidatorImpl playerIDValidator;

    @Test
    void validateID_Successful()
    {
        String playerID = "1";

        when(playerRepositoryMock.existsById(playerID)).thenReturn(true);
        playerIDValidator.validateID(playerID);
        verify(playerRepositoryMock).existsById(playerID);
    }

    @Test
    void validateID_PlayerDoesNotExist_ThrowException()
    {
        String playerID = "1";

        when(playerRepositoryMock.existsById(playerID)).thenReturn(false);
        assertThrows(InvalidPlayerException.class, ()->playerIDValidator.validateID(playerID));
    }
}