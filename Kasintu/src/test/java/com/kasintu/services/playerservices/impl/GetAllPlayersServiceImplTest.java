package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.GetAllPlayerResponseDTO;
import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPlayersServiceImplTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @InjectMocks
    private GetAllPlayersServiceImpl getAllPlayersService;

    @Test
    void getAllPlayers_ReturnPlayers()
    {
        List<Player> players = List.of(
                Player.builder()
                        .playerID("1")
                        .user(
                                User.builder()
                                        .userID("1")
                                        .build()
                        )
                        .coin(100)
                        .build(),
                Player.builder()
                        .playerID("2")
                        .user(
                                User.builder()
                                        .userID("2")
                                        .build()
                        )
                        .coin(100)
                        .build()
        );
        when(playerRepositoryMock.findAll()).thenReturn(players);

        GetAllPlayerResponseDTO actualResponse = getAllPlayersService.getAllPlayers();
        GetAllPlayerResponseDTO expectedResponse = GetAllPlayerResponseDTO.builder()
                .players(
                        List.of(
                                PlayerDTO.builder()
                                        .playerID("1")
                                        .userID("1")
                                        .coin(100)
                                        .build(),
                                PlayerDTO.builder()
                                        .playerID("2")
                                        .userID("2")
                                        .coin(100)
                                        .build()
                        )
                )
                .build();
        assertEquals(expectedResponse, actualResponse);
        verify(playerRepositoryMock).findAll();
    }
}