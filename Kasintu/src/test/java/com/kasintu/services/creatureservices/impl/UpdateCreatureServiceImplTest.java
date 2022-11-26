package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.UpdateCreatureRequestDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.creatureservices.exception.InvalidCreatureException;
import com.kasintu.services.rarityservices.RarityIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCreatureServiceImplTest {

    @Mock
    private CreatureRepository creatureRepositoryMock;

    @InjectMocks
    private UpdateCreatureServiceImpl updateCreatureServiceImpl;

    @Mock
    private RarityIDValidator rarityIDValidator;

    @Test
    void updateCreature_Successful()
    {
        Creature creatureBeforeUpdate = Creature.builder()
                .creatureID("1")
                .name("Creature")
                .rarity(Rarity.builder().rarityID("common").build())
                .description("")
                .build();
        when(creatureRepositoryMock.findById("1")).thenReturn(Optional.of((creatureBeforeUpdate)));

        UpdateCreatureRequestDTO request = UpdateCreatureRequestDTO.builder()
                .creatureID("1")
                .name("NewCreature")
                .rarityID("uncommon")
                .description("new")
                .build();
        updateCreatureServiceImpl.updateCreature(request);

        Creature expectedSavedCreature = Creature.builder()
                .creatureID("1")
                .name("NewCreature")
                .rarity(Rarity.builder().rarityID("uncommon").build())
                .description("new")
                .build();

        verify(creatureRepositoryMock).findById("1");
        verify(creatureRepositoryMock).save(expectedSavedCreature);
    }

    @Test
    void updateCreature_CreatureNotFound_ThrowException()
    {
        when(creatureRepositoryMock.findById("1")).thenReturn(Optional.empty());

        UpdateCreatureRequestDTO request = UpdateCreatureRequestDTO.builder()
                .creatureID("1")
                .name("NewCreature")
                .rarityID("uncommon")
                .description("new")
                .build();
        assertThrows(InvalidCreatureException.class, () -> updateCreatureServiceImpl.updateCreature(request));

        verify(creatureRepositoryMock).findById("1");
        verifyNoInteractions(rarityIDValidator);
    }

    @Test
    void updateCreature_NameAlreadyExist_ThrowException()
    {
        Creature creatureBeforeUpdate = Creature.builder()
                .creatureID("1")
                .name("Creature")
                .rarity(Rarity.builder().rarityID("common").build())
                .description("")
                .build();
        when(creatureRepositoryMock.findById("1")).thenReturn(Optional.of((creatureBeforeUpdate)));
        when(creatureRepositoryMock.existsByName("NewCreature")).thenReturn(true);

        UpdateCreatureRequestDTO request = UpdateCreatureRequestDTO.builder()
                .creatureID("1")
                .name("NewCreature")
                .rarityID("uncommon")
                .description("new")
                .build();
        assertThrows(InvalidCreatureException.class, () -> updateCreatureServiceImpl.updateCreature(request));
        verify(creatureRepositoryMock).findById("1");
        verify(creatureRepositoryMock).existsByName("NewCreature");
        verifyNoInteractions(rarityIDValidator);
    }

}