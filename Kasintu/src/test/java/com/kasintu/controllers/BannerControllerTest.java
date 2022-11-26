package com.kasintu.controllers;

import com.kasintu.dtos.bannerdtos.*;
import com.kasintu.dtos.pullratedtos.RarityPullRatePercentageDTO;
import com.kasintu.services.bannerservices.*;
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
@WebMvcTest(BannerController.class)
class BannerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateBannerService createBannerService;
    @MockBean
    private DeleteBannerService deleteBannerService;
    @MockBean
    private GetAllBannersService getAllBannersService;
    @MockBean
    private GetBannerService getBannerService;
    @MockBean
    private UpdateBannerService updateBannerService;
    @MockBean
    private BannerPullRatesInformationService bannerPullRatesInformationService;

    @Test
    void getAllBanners_Return200() throws Exception {
        GetAllBannersResponseDTO response = GetAllBannersResponseDTO.builder()
                .banners(
                        List.of(
                                BannerDTO.builder()
                                        .bannerID("1")
                                        .pullRates(List.of())
                                        .cost(100)
                                        .build(),
                                BannerDTO.builder()
                                        .bannerID("2")
                                        .pullRates(List.of())
                                        .cost(100)
                                        .build()
                        )
                )
                .build();

        when(getAllBannersService.getAllBanners()).thenReturn(response);

        mockMvc.perform(get("/banner"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "banners":
                                    [
                                        {
                                            "bannerID": "1",
                                            "pullRates": [],
                                            "cost": 100
                                        },
                                        {
                                            "bannerID": "2",
                                            "pullRates": [],
                                            "cost": 100
                                        }
                                    ]
                                }
                                """
                ));
        verify(getAllBannersService).getAllBanners();
    }

    @Test
    void getBanner_Return200() throws Exception {
        BannerDTO bannerDTO = BannerDTO.builder()
                .bannerID("1")
                .pullRates(List.of())
                .cost(100)
                .build();
        when(getBannerService.getBanner("1")).thenReturn(Optional.of(bannerDTO));

        mockMvc.perform(get("/banner/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {
                        "bannerID": "1",
                        "pullRates": [],
                        "cost": 100
                    }
                """));
        verify(getBannerService).getBanner("1");
    }

    @Test
    void getBanner_Return404() throws Exception {
        when(getBannerService.getBanner("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/banner/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(getBannerService).getBanner("1");
    }

    @Test
    void getBannerPullRateInformation_Return200() throws Exception {
        BannerPullRatesInformationResponseDTO response = BannerPullRatesInformationResponseDTO.builder()
                .ratesInformation(
                        List.of(
                                RarityPullRatePercentageDTO.builder()
                                        .rarity(null)
                                        .percentage("40%")
                                        .build(),
                                RarityPullRatePercentageDTO.builder()
                                        .rarity(null)
                                        .percentage("50%")
                                        .build()
                                )
                )
                .build();
        when(bannerPullRatesInformationService.bannerPullRatesInformationService("1")).thenReturn(response);

        mockMvc.perform(get("/banner/pullRateInfo/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json(
                        """
                                {
                                    "ratesInformation":
                                    [
                                        {
                                            "rarity": null,
                                            "percentage": "40%"
                                        },
                                        {
                                            "rarity": null,
                                            "percentage": "50%"
                                        }
                                    ]
                                }
                                """
                ));
        verify(bannerPullRatesInformationService).bannerPullRatesInformationService("1");
    }

    @Test
    void createBanner_Return201() throws Exception {
        CreateBannerRequestDTO expectedRequest = CreateBannerRequestDTO.builder()
                .pullRateIDs(List.of("1", "2"))
                .cost(100)
                .build();
        when(createBannerService.createBanner(expectedRequest))
                .thenReturn(
                        CreateBannerResponseDTO.builder()
                                .bannerID("1")
                                .build()
                );

        mockMvc.perform(post("/banner")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "pullRateIDs": ["1", "2"],
                            "cost": "100"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                    {
                        "bannerID": "1"
                    }
                """));
        verify(createBannerService).createBanner(expectedRequest);
    }

    @Test
    void deleteBanner_Return204() throws Exception {
        mockMvc.perform(delete("/banner/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(deleteBannerService).deleteBanner("1");
    }

    @Test
    void updateBanner_Return204() throws Exception {
        mockMvc.perform(put("/banner/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "bannerID": "1",
                            "pullRateIDs": ["1", "2"],
                            "cost": "100"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateBannerRequestDTO expectedRequest = UpdateBannerRequestDTO.builder()
                .bannerID("1")
                .pullRateIDs(List.of("1", "2"))
                .cost(100)
                .build();
        verify(updateBannerService).updateBanner(expectedRequest);
    }
}