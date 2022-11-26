package com.kasintu.services.creatureservices.impl;

import com.kasintu.repositories.CreatureRepository;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCreatureServiceImplTest {

    @Mock
    private CreatureRepository creatureRepositoryMock;

    @InjectMocks
    private DeleteCreatureServiceImpl deleteCreatureServiceImpl;

    @Test
    void deleteCreature_Successful()
    {
        String id = "1";

        when(creatureRepositoryMock.existsById(id)).thenReturn(true);
        deleteCreatureServiceImpl.deleteCreature(id);

        verify(creatureRepositoryMock).deleteById(id);
        verify(creatureRepositoryMock).existsById(id);
    }

    @Test
    void deleteCreature_IDNotFound_ThrowException()
    {
        String id = "1";

        when(creatureRepositoryMock.existsById(id)).thenReturn(false);

        assertThrows(InvalidCreatureException.class, ()->deleteCreatureServiceImpl.deleteCreature(id));
    }
}