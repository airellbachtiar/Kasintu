package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerCheckResponseDTO;
import com.kasintu.dtos.bannerdtos.CreateBannerRequestDTO;
import com.kasintu.dtos.bannerdtos.CreateBannerResponseDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.bannerservices.BannerCheckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateBannerServiceImplTest {

    @Mock
    private BannerRepository bannerRepositoryMock;
    @Mock
    private BannerCheckService bannerCheckService;

    @InjectMocks
    private CreateBannerServiceImpl createBannerService;

    @Captor
    private ArgumentCaptor<Banner> bannerCaptor;

    @Test
    void createBanner()
    {
        BannerCheckResponseDTO bannerCheckResponse = BannerCheckResponseDTO.builder()
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
        Banner savedBanner = Banner.builder()
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
        when(bannerCheckService.checkBanner(any())).thenReturn(bannerCheckResponse);
        when(bannerRepositoryMock.save(any())).thenReturn(savedBanner);

        CreateBannerRequestDTO request = CreateBannerRequestDTO.builder()
                .cost(100)
                .pullRateIDs(
                        List.of(
                                "1", "2"
                        )
                )
                .build();
        CreateBannerResponseDTO actualResponse = createBannerService.createBanner(request);
        CreateBannerResponseDTO expectedResponse = CreateBannerResponseDTO.builder()
                .bannerID("1")
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(bannerRepositoryMock).save(bannerCaptor.capture());
    }
}