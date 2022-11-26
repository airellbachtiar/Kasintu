package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
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
class GetPlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @InjectMocks
    private GetPlayerServiceImpl getPlayerService;

    @Test
    void getPlayer_ReturnPlayer() {
        Player player = Player.builder()
                .playerID("1")
                .coin(1000)
                .user(
                        User.builder()
                                .userID("1")
                                .build()
                )
                .build();
        when(playerRepositoryMock.findByUser_UserID("1")).thenReturn(player);

        Optional<PlayerDTO> playerOptional = getPlayerService.getPlayer("1");
        PlayerDTO actualResult = playerOptional.orElseThrow();

        PlayerDTO expectedResult = PlayerDTO.builder()
                .playerID("1")
                .userID("1")
                .coin(1000)
                .build();
        assertEquals(expectedResult, actualResult);
        verify(playerRepositoryMock).findByUser_UserID("1");
    }
}