package com.kasintu.controllers;

import com.kasintu.dtos.playerdtos.*;
import com.kasintu.services.playerservices.CreatePlayerService;
import com.kasintu.services.playerservices.GetAllPlayersService;
import com.kasintu.services.playerservices.GetPlayerService;
import com.kasintu.services.playerservices.UpdatePlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePlayerService createPlayerService;
    @MockBean
    private GetAllPlayersService getAllPlayersService;
    @MockBean
    private GetPlayerService getPlayerService;
    @MockBean
    private UpdatePlayerService updatePlayerService;

    @Test
    void getAllPlayers_Return200() throws Exception {
        GetAllPlayerResponseDTO response = GetAllPlayerResponseDTO.builder()
                .players(
                        List.of(
                                PlayerDTO.builder()
                                        .userID("1")
                                        .playerID("3")
                                        .coin(100)
                                        .build(),
                                PlayerDTO.builder()
                                        .userID("2")
                                        .playerID("4")
                                        .coin(100)
                                        .build()
                        )
                )
                .build();
        when(getAllPlayersService.getAllPlayers()).thenReturn(response);

        mockMvc.perform(get("/players"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "players":
                                    [
                                        {
                                            "userID": "1",
                                            "playerID": "3",
                                            "coin": 100
                                        },
                                        {
                                            "userID": "2",
                                            "playerID": "4",
                                            "coin": 100
                                        }
                                    ]
                                }
                                """
                ));
        verify(getAllPlayersService).getAllPlayers();
    }

    @Test
    void getPlayer_Return200() throws Exception {
        PlayerDTO player = PlayerDTO.builder()
                .userID("1")
                .playerID("2")
                .coin(100)
                .build();
        when(getPlayerService.getPlayer("1")).thenReturn(Optional.of(player));

        mockMvc.perform(get("/players/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "userID": "1",
                                    "playerID": "2",
                                    "coin": 100
                                }
                                """
                ));
        verify(getPlayerService).getPlayer("1");
    }

    @Test
    void getPlayer_Return404() throws Exception {
        when(getPlayerService.getPlayer("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/players/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getPlayerService).getPlayer("1");
    }

    @Test
    void createPlayer_Return201() throws Exception {
        CreatePlayerRequestDTO expectedRequest = CreatePlayerRequestDTO.builder()
                .username("username")
                .password("password")
                .email("email")
                .build();
        when(createPlayerService.createPlayer(expectedRequest)).thenReturn(
                CreatePlayerResponseDTO.builder()
                        .playerID("1")
                        .build()
        );

        mockMvc.perform(post("/players")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "username": "username",
                            "password": "password",
                            "email": "email"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                    {
                        "playerID": "1"
                    }
                """));
        verify(createPlayerService).createPlayer(expectedRequest);
    }

    @Test
    void updatePlayer_Return204() throws Exception {
        mockMvc.perform(put("/players/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "playerID": "1",
                            "coin": 100
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdatePlayerRequestDTO expectedRequest = UpdatePlayerRequestDTO.builder()
                .playerID("1")
                .coin(100)
                .build();
        verify(updatePlayerService).updatePlayer(expectedRequest);
    }
}