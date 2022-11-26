package com.kasintu.controllers;

import com.kasintu.dtos.pullratedtos.*;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.services.pullrateservices.*;
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
@WebMvcTest(PullRateController.class)
class PullRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePullRateService createPullRateService;
    @MockBean
    private DeletePullRateService deletePullRateService;
    @MockBean
    private GetAllPullRatesService getAllPullRatesService;
    @MockBean
    private GetPullRateService getPullRateService;
    @MockBean
    private UpdatePullRateService updatePullRateService;

    private RarityDTO commonRarity()
    {
        return RarityDTO.builder()
                .rarityID("1")
                .rarityType("Common")
                .rarityLevel(0)
                .build();
    }

    @Test
    void getAllPullRates_Return200() throws Exception {
        GetAllPullRatesResponseDTO response = GetAllPullRatesResponseDTO.builder()
                .pullRates(
                        List.of(
                                PullRateDTO.builder()
                                        .pullRateID("1")
                                        .rarity(commonRarity())
                                        .rateValue(1)
                                        .build(),
                                PullRateDTO.builder()
                                        .pullRateID("2")
                                        .rarity(commonRarity())
                                        .rateValue(1)
                                        .build()
                        )
                )
                .build();
        when(getAllPullRatesService.getAllPullRates()).thenReturn(response);

        mockMvc.perform(get("/pullRates"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "pullRates":
                                    [
                                        {
                                            "pullRateID": "1",
                                            "rarity": {
                                                "rarityID": "1",
                                                "rarityType": "Common",
                                                "rarityLevel": 0
                                            },
                                            "rateValue": 1
                                        },
                                        {
                                            "pullRateID": "2",
                                            "rarity": {
                                                "rarityID": "1",
                                                "rarityType": "Common",
                                                "rarityLevel": 0
                                            },
                                            "rateValue": 1
                                        }
                                    ]
                                }
                                """
                ));
        verify(getAllPullRatesService).getAllPullRates();
    }

    @Test
    void getPullRate_Return200() throws Exception {
        PullRateDTO pullRate = PullRateDTO.builder()
                .pullRateID("1")
                .rarity(commonRarity())
                .rateValue(1)
                .build();
        when(getPullRateService.getPullRate("1")).thenReturn(Optional.of(pullRate));

        mockMvc.perform(get("/pullRates/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {
                        "pullRateID": "1",
                        "rarity": {
                            "rarityID": "1",
                            "rarityType": "Common",
                            "rarityLevel": 0
                            },
                        "rateValue": 1
                    }
                """));
        verify(getPullRateService).getPullRate("1");
    }

    @Test
    void getPullRate_Return404() throws Exception {
        when(getPullRateService.getPullRate("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/pullRates/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getPullRateService).getPullRate("1");
    }

    @Test
    void createPullRate_Return201() throws Exception {
        CreatePullRateRequestDTO expectedRequest = CreatePullRateRequestDTO.builder()
                .rarityID("1")
                .rateValue(1)
                .build();
        when(createPullRateService.createPullRate(expectedRequest))
                .thenReturn(
                        CreatePullRateResponseDTO.builder()
                                .pullRateID("1")
                                .build()
                );

        mockMvc.perform(post("/pullRates")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "rarityID": "1",
                            "rateValue": "1"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                    {
                        "pullRateID": "1"
                    }
                """));
        verify(createPullRateService).createPullRate(expectedRequest);
    }

    @Test
    void deletePullRate_Return204() throws Exception {
        mockMvc.perform(delete("/pullRates/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(deletePullRateService).deletePullRate("1");
    }

    @Test
    void updatePullRate_Return204() throws Exception {
        mockMvc.perform(put("/pullRates/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "pullRateID": "1",
                            "rarityID": "2",
                            "rateValue": "1"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdatePullRateRequestDTO expectedRequest = UpdatePullRateRequestDTO.builder()
                .pullRateID("1")
                .rarityID("2")
                .rateValue(1)
                .build();
        verify(updatePullRateService).updatePullRate(expectedRequest);
    }
}