package com.kasintu.controllers;

import com.kasintu.dtos.raritydtos.*;
import com.kasintu.services.rarityservices.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RarityController.class)
class RarityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllRaritiesService getAllRaritiesServiceMock;
    @MockBean
    private CreateRarityService createRarityServiceMock;
    @MockBean
    private GetRarityService getRarityService;
    @MockBean
    private UpdateRarityService updateRarityService;
    @MockBean
    private DeleteRarityService deleteRarityService;

    @Test
    void getAllRarities_Return200() throws Exception {

        GetAllRaritiesResponseDTO response = GetAllRaritiesResponseDTO.builder().rarities(List.of(RarityDTO.builder().rarityID("common").rarityType("common").build(),
                RarityDTO.builder().rarityID("uncommon").rarityType("uncommon").build())).build();

        when(getAllRaritiesServiceMock.getAllRarities()).thenReturn(response);

        mockMvc.perform(get("/rarities")).andDo(print()).andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        {
                            "rarities":
                            [
                                {
                                    "rarityID": "common",
                                    "rarityType": "common"
                                },
                                {
                                    "rarityID": "uncommon",
                                    "rarityType": "uncommon"
                                }
                            ]
                        }
                        """));

        verify(getAllRaritiesServiceMock).getAllRarities();
        verifyNoInteractions(createRarityServiceMock);
    }

    @Test
    void createRarity_Return201_Successful() throws Exception {
        CreateRarityRequestDTO expectedRequest = CreateRarityRequestDTO.builder()
                .rarityType("Common")
                .rarityLevel(0)
                .build();
        when(createRarityServiceMock.createRarity(expectedRequest))
                .thenReturn(
                        CreateRarityResponseDTO.builder()
                                .rarityID("1")
                                .build()
                );

        mockMvc.perform(post("/rarities")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "rarityType": "Common",
                            "rarityLevel": "0"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                    {"rarityID": "1"}
                """));

        verify(createRarityServiceMock).createRarity(expectedRequest);
    }

    @Test
    void getRarity_Return200WithRarity_Successful() throws Exception {
        RarityDTO rarityDTO = RarityDTO.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
        when(getRarityService.getRarity("1")).thenReturn(Optional.of(rarityDTO));

        mockMvc.perform(get("/rarities/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {
                        "rarityID": "1",
                        "rarityType": "Common",
                        "rarityLevel": 0
                    }
                """));

        verify(getRarityService).getRarity("1");
    }

    @Test
    void getRarity_Return404_RarityNotFound() throws Exception {
        when(getRarityService.getRarity("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/rarities/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getRarityService).getRarity("1");
    }

    @Test
    void deleteRarity_Return204() throws Exception {
        mockMvc.perform(delete("/rarities/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(deleteRarityService).deleteRarity("1");
    }

    @Test
    void UpdateRarity_Return204() throws Exception
    {
        mockMvc.perform(put("/rarities/1")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "rarityID": "1",
                            "rarityType": "Common",
                            "rarityLevel": 0
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateRarityRequestDTO expectedRequest = UpdateRarityRequestDTO.builder()
                        .rarityID("1")
                        .rarityType("Common")
                        .rarityLevel(0)
                        .build();
        verify(updateRarityService).updateRarity(expectedRequest);
    }
}