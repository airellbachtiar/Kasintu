package com.kasintu.controllers;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.services.gameservices.PlayerSummonABannerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerSummonABannerService playerSummonABannerService;

    private RarityDTO commonRarity()
    {
        return RarityDTO.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
    }

    @Test
    void summonCreatures_Return200() throws Exception {
        List<CreatureDTO> expectedResponse = List.of(
                CreatureDTO.builder()
                        .creatureID("1")
                        .rarity(commonRarity())
                        .name("Test")
                        .description("none")
                        .build(),
                CreatureDTO.builder()
                        .creatureID("2")
                        .rarity(commonRarity())
                        .name("Test")
                        .description("none")
                        .build()
        );
        when(playerSummonABannerService.playerSummonABanner("1", "1", 2)).thenReturn(expectedResponse);

        mockMvc.perform(post("/game/1")
                .contentType(APPLICATION_JSON_VALUE)
                .content("""
                        {
                            "bannerID": "1",
                            "summonTimes": "2"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
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
                """));
        verify(playerSummonABannerService).playerSummonABanner("1", "1", 2);
    }
}