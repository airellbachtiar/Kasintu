package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.UpdatePlayerRequestDTO;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.playerservices.exception.InvalidPlayerException;
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
class UpdatePlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @InjectMocks
    private UpdatePlayerServiceImpl updatePlayerService;

    @Test
    void updatePlayer_Successful()
    {
        Player playerBeforeUpdate = Player.builder()
                .playerID("1")
                .coin(1000)
                .user(
                        User.builder()
                                .userID("1")
                                .build()
                )
                .build();
        when(playerRepositoryMock.findById("1")).thenReturn(Optional.of(playerBeforeUpdate));

        UpdatePlayerRequestDTO request = UpdatePlayerRequestDTO.builder()
                .playerID("1")
                .coin(900)
                .build();
        updatePlayerService.updatePlayer(request);

        Player expectedSavedPlayer = Player.builder()
                .playerID("1")
                .coin(900)
                .user(
                        User.builder()
                                .userID("1")
                                .build()
                )
                .build();
        verify(playerRepositoryMock).findById("1");
        verify(playerRepositoryMock).save(expectedSavedPlayer);
    }

    @Test
    void updatePlayer_PlayerNotFound_ThrowException()
    {
        when(playerRepositoryMock.findById("1")).thenReturn(Optional.empty());

        UpdatePlayerRequestDTO request = UpdatePlayerRequestDTO.builder()
                .playerID("1")
                .coin(900)
                .build();
        assertThrows(InvalidPlayerException.class, ()->updatePlayerService.updatePlayer(request));

        verify(playerRepositoryMock).findById("1");
    }
}