package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.repositories.OwnedCreatureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteOwnedCreatureServiceImplTest {

    @Mock
    private OwnedCreatureRepository ownedCreatureRepository;

    @InjectMocks
    private DeleteOwnedCreatureServiceImpl deleteOwnedCreatureServiceImpl;

    @Test
    void deleteOwnedCreature() {
        String id = "1";
        
        deleteOwnedCreatureServiceImpl.deleteOwnedCreature(id);
        verify(ownedCreatureRepository).deleteById(id);
    }
}