package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerPullRatesInformationResponseDTO;
import com.kasintu.dtos.pullratedtos.RarityPullRatePercentageDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.bannerservices.exception.InvalidBannerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BannerPullRatesInformationServiceImplTest {

    @Mock
    private BannerRepository bannerRepositoryMock;

    @InjectMocks
    private BannerPullRatesInformationServiceImpl bannerPullRatesInformationService;

    @Test
    void bannerPullRatesInformationService()
    {
        Banner banner = Banner.builder()
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
                .build();
        when(bannerRepositoryMock.findById("1")).thenReturn(Optional.of(banner));

        BannerPullRatesInformationResponseDTO actualResponse = bannerPullRatesInformationService.bannerPullRatesInformationService("1");
        BannerPullRatesInformationResponseDTO expectedResponse = BannerPullRatesInformationResponseDTO.builder()
                .ratesInformation(
                        List.of(
                                RarityPullRatePercentageDTO.builder()
                                        .percentage("50.00%")
                                        .rarity(
                                                Rarity.builder()
                                                        .rarityID("1")
                                                        .build()
                                        )
                                        .build(),
                                RarityPullRatePercentageDTO.builder()
                                        .percentage("50.00%")
                                        .rarity(
                                                Rarity.builder()
                                                        .rarityID("2")
                                                        .build()
                                        )
                                        .build()
                        )
                )
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(bannerRepositoryMock).findById("1");
    }

    @Test
    void bannerPullRatesInformationService_BannerNotFound_ThrowException()
    {
        when(bannerRepositoryMock.findById("1")).thenReturn(Optional.empty());

        assertThrows(InvalidBannerException.class, ()->bannerPullRatesInformationService.bannerPullRatesInformationService("1"));
        verify(bannerRepositoryMock).findById("1");
    }
}