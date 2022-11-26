package com.kasintu.controllers;

import com.kasintu.dtos.creaturedtos.*;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.services.creatureservices.*;
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
@WebMvcTest(CreatureController.class)
class CreatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAllCreaturesService getAllCreaturesServiceMock;
    @MockBean
    private GetCreatureService getCreatureServiceMock;
    @MockBean
    private CreateCreatureService createCreatureServiceMock;
    @MockBean
    private UpdateCreatureService updateCreatureServiceMock;
    @MockBean
    private DeleteCreatureService deleteCreatureServiceMock;

    private RarityDTO getCommonRarityDTO()
    {
        return RarityDTO.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
    }

    @Test
    void GetAllCreatures_NoFilter_Return200() throws Exception {
        GetAllCreaturesResponseDTO response = GetAllCreaturesResponseDTO.builder()
                .creatures(List.of(
                        CreatureDTO.builder()
                                .creatureID("1")
                                .rarity(getCommonRarityDTO())
                                .name("Test")
                                .description("none")
                                .build(),
                        CreatureDTO.builder()
                                .creatureID("2")
                                .rarity(getCommonRarityDTO())
                                .name("Test")
                                .description("none")
                                .build()
                ))
                .build();

        GetAllCreaturesRequestDTO request = GetAllCreaturesRequestDTO.builder().build();

        when(getAllCreaturesServiceMock.getAllCreatures(request)).thenReturn(response);

        mockMvc.perform(get("/creatures"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "creatures":
                                    [
                                        {
                                            "creatureID": "1",
                                            "name": "Test",
                                            "rarity": {
                                                "rarityID": "1",
                                                "rarityType": "Common",
                                                "rarityLevel": 0
                                            },
                                            "description": "none"
                                        },
                                        {
                                            "creatureID": "2",
                                            "name": "Test",
                                            "rarity": {
                                                "rarityID": "1",
                                                "rarityType": "Common",
                                                "rarityLevel": 0
                                            },
                                            "description": "none"
                                        }
                                    ]
                                }
                                """
                ));
        verify(getAllCreaturesServiceMock).getAllCreatures(request);
    }

    @Test
    void GetAllCreatures_RarityFilter_Return200() throws Exception {
        GetAllCreaturesResponseDTO response = GetAllCreaturesResponseDTO.builder()
                .creatures(List.of(
                        CreatureDTO.builder()
                                .creatureID("1")
                                .rarity(getCommonRarityDTO())
                                .name("Test")
                                .description("none")
                                .build(),
                        CreatureDTO.builder()
                                .creatureID("2")
                                .rarity(getCommonRarityDTO())
                                .name("Test")
                                .description("none")
                                .build()
                ))
                .build();

        GetAllCreaturesRequestDTO request = GetAllCreaturesRequestDTO.builder()
                .rarityID("1")
                .build();

        when(getAllCreaturesServiceMock.getAllCreatures(request)).thenReturn(response);

        mockMvc.perform(get("/creatures").param("rarity", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "creatures":
                                    [
                                        {
                                            "creatureID": "1",
                                            "name": "Test",
                                            "rarity": {
                                                "rarityID": "1",
                                                "rarityType": "Common",
                                                "rarityLevel": 0
                                            },
                                            "description": "none"
                                        },
                                        {
                                            "creatureID": "2",
                                            "name": "Test",
                                            "rarity": {
                                                "rarityID": "1",
                                                "rarityType": "Common",
                                                "rarityLevel": 0
                                            },
                                            "description": "none"
                                        }
                                    ]
                                }
                                """
                ));
        verify(getAllCreaturesServiceMock).getAllCreatures(request);
    }

    @Test
    void GetCreature_Return200WithCreature_Successful() throws Exception {
        CreatureDTO creatureDTO = CreatureDTO.builder()
                .creatureID("1")
                .rarity(getCommonRarityDTO())
                .name("Test")
                .description("none")
                .build();
        when(getCreatureServiceMock.getCreature("1")).thenReturn(Optional.of(creatureDTO));

        mockMvc.perform(get("/creatures/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {
                        "creatureID": "1",
                        "name": "Test",
                        "rarity": {
                            "rarityID": "1",
                            "rarityType": "Common",
                            "rarityLevel": 0
                            },
                        "description": "none"
                    }
                """));
        verify(getCreatureServiceMock).getCreature("1");
    }

    @Test
    void GetCreature_Return404_CreatureNotFound() throws Exception {
        when(getCreatureServiceMock.getCreature("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/creatures/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getCreatureServiceMock).getCreature("1");
    }

    @Test
    void CreateCreature_Return201_Successful() throws Exception {
        CreateCreatureRequestDTO expectedRequest = CreateCreatureRequestDTO.builder()
                .name("Creature")
                .rarityID("1")
                .description("none")
                .build();
        when(createCreatureServiceMock.createCreature(expectedRequest))
                .thenReturn(
                        CreateCreatureResponseDTO.builder()
                                .creatureID("1")
                                .build()
                );

        mockMvc.perform(post("/creatures")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "name": "Creature",
                            "rarityID": "1",
                            "description": "none"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                    {
                        "creatureID": "1"
                    }
                """));

        verify(createCreatureServiceMock).createCreature(expectedRequest);
    }

    @Test
    void DeleteCreature_Return204() throws Exception {
        mockMvc.perform(delete("/creatures/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(deleteCreatureServiceMock).deleteCreature("1");
    }

    @Test
    void UpdateCreature_Return204() throws Exception {
        mockMvc.perform(put("/creatures/1")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "creatureID": "1",
                            "name": "New",
                            "rarityID": "2",
                            "description": "edited"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateCreatureRequestDTO expectedRequest = UpdateCreatureRequestDTO.builder()
                .creatureID("1")
                .name("New")
                .rarityID("2")
                .description("edited")
                .build();
        verify(updateCreatureServiceMock).updateCreature(expectedRequest);
    }
}
