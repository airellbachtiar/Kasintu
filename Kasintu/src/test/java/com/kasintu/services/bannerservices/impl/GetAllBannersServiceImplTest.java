package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.bannerdtos.GetAllBannersResponseDTO;
import com.kasintu.dtos.pullratedtos.PullRateDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
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
class GetAllBannersServiceImplTest {

    @Mock
    private BannerRepository bannerRepository;

    @InjectMocks
    private GetAllBannersServiceImpl getAllBannersService;

    @Test
    void getAllBanners()
    {
        List<Banner> banners = List.of(
                Banner.builder()
                        .bannerID("1")
                        .cost(100)
                        .pullRates(
                                List.of(
                                        PullRate.builder()
                                                .pullRateID("1")
                                                .rateValue(1)
                                                .rarity(
                                                        Rarity.builder()
                                                                .rarityID("1")
                                                                .build()
                                                )
                                                .build(),
                                        PullRate.builder()
                                                .pullRateID("2")
                                                .rateValue(1)
                                                .rarity(
                                                        Rarity.builder()
                                                                .rarityID("2")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                        .build(),
                Banner.builder()
                        .bannerID("2")
                        .cost(100)
                        .pullRates(
                                List.of(
                                        PullRate.builder()
                                                .pullRateID("1")
                                                .rateValue(1)
                                                .rarity(
                                                        Rarity.builder()
                                                                .rarityID("1")
                                                                .build()
                                                )
                                                .build(),
                                        PullRate.builder()
                                                .pullRateID("2")
                                                .rateValue(1)
                                                .rarity(
                                                        Rarity.builder()
                                                                .rarityID("2")
                                                                .build()
                                                )
                                                .build()
                                )
                        )
                        .build()
        );
        when(bannerRepository.findAll()).thenReturn(banners);

        GetAllBannersResponseDTO actualResponse = getAllBannersService.getAllBanners();
        GetAllBannersResponseDTO expectedResponse = GetAllBannersResponseDTO.builder()
                .banners(
                        List.of(
                                BannerDTO.builder()
                                        .bannerID("1")
                                        .cost(100)
                                        .pullRates(
                                                List.of(
                                                        PullRateDTO.builder()
                                                                .pullRateID("1")
                                                                .rateValue(1)
                                                                .rarity(
                                                                        RarityDTO.builder()
                                                                                .rarityID("1")
                                                                                .build()
                                                                )
                                                                .build(),
                                                        PullRateDTO.builder()
                                                                .pullRateID("2")
                                                                .rateValue(1)
                                                                .rarity(
                                                                        RarityDTO.builder()
                                                                                .rarityID("2")
                                                                                .build()
                                                                )
                                                                .build()
                                                )
                                        )
                                        .build(),
                                BannerDTO.builder()
                                        .bannerID("2")
                                        .cost(100)
                                        .pullRates(
                                                List.of(
                                                        PullRateDTO.builder()
                                                                .pullRateID("1")
                                                                .rateValue(1)
                                                                .rarity(
                                                                        RarityDTO.builder()
                                                                                .rarityID("1")
                                                                                .build()
                                                                )
                                                                .build(),
                                                        PullRateDTO.builder()
                                                                .pullRateID("2")
                                                                .rateValue(1)
                                                                .rarity(
                                                                        RarityDTO.builder()
                                                                                .rarityID("2")
                                                                                .build()
                                                                )
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                )
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(bannerRepository).findAll();
    }
}