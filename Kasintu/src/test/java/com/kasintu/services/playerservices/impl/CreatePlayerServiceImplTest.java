package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.CreatePlayerRequestDTO;
import com.kasintu.dtos.playerdtos.CreatePlayerResponseDTO;
import com.kasintu.repositories.InventoryRepository;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.userservices.CreateUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepositoryMock;
    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private CreateUserService createUserService;

    @InjectMocks
    private CreatePlayerServiceImpl createPlayerService;

    @Captor
    private ArgumentCaptor<Player> playerCaptor;

    @Test
    void createPlayer_ReturnNewPlayer()
    {
        Player savedPlayer = Player.builder()
                .playerID("1")
                .coin(1000)
                .user(
                        User.builder()
                                .userID("1")
                                .build()
                )
                .build();
        when(playerRepositoryMock.save(any())).thenReturn(savedPlayer);

        CreatePlayerRequestDTO request = CreatePlayerRequestDTO.builder()
                .email("user@email.com")
                .password("password")
                .username("username")
                .build();
        CreatePlayerResponseDTO actualResponse = createPlayerService.createPlayer(request);
        CreatePlayerResponseDTO expectedResponse = CreatePlayerResponseDTO.builder()
                .playerID("1")
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(playerRepositoryMock).save(playerCaptor.capture());
    }
}