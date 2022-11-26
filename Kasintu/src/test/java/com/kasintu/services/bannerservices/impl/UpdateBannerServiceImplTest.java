package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerCheckResponseDTO;
import com.kasintu.dtos.bannerdtos.UpdateBannerRequestDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.bannerservices.BannerCheckService;
import com.kasintu.services.bannerservices.exception.InvalidBannerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateBannerServiceImplTest {

    @Mock
    private BannerRepository bannerRepositoryMock;
    @Mock
    private BannerCheckService bannerCheckService;

    @InjectMocks
    private UpdateBannerServiceImpl updateBannerService;

    @Test
    void updateBanner()
    {
        Banner bannerBeforeUpdate = Banner.builder()
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
        BannerCheckResponseDTO bannerCheckResponse = BannerCheckResponseDTO.builder()
                .pullRates(
                        List.of(
                                PullRate.builder()
                                        .pullRateID("3")
                                        .rateValue(1)
                                        .rarity(
                                                Rarity.builder()
                                                        .rarityID("1")
                                                        .build()
                                        )
                                        .build(),
                                PullRate.builder()
                                        .pullRateID("4")
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
        when(bannerRepositoryMock.findById("1")).thenReturn(Optional.of(bannerBeforeUpdate));
        when(bannerCheckService.checkBanner(any())).thenReturn(bannerCheckResponse);

        UpdateBannerRequestDTO request = UpdateBannerRequestDTO.builder()
                .bannerID("1")
                .cost(200)
                .pullRateIDs(
                        List.of(
                                "3", "4"
                        )
                )
                .build();
        updateBannerService.updateBanner(request);

        Banner expectedSavedBanner = Banner.builder()
                .bannerID("1")
                .cost(200)
                .pullRates(
                        List.of(
                                PullRate.builder()
                                        .pullRateID("3")
                                        .rateValue(1)
                                        .rarity(
                                                Rarity.builder()
                                                        .rarityID("1")
                                                        .build()
                                        )
                                        .build(),
                                PullRate.builder()
                                        .pullRateID("4")
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
        verify(bannerRepositoryMock).findById("1");
        verify(bannerRepositoryMock).save(expectedSavedBanner);
    }

    @Test
    void updateBanner_BannerNotFound_ThrowException()
    {
        when(bannerRepositoryMock.findById("1")).thenReturn(Optional.empty());
        UpdateBannerRequestDTO request = UpdateBannerRequestDTO.builder()
                .bannerID("1")
                .cost(200)
                .pullRateIDs(
                        List.of(
                                "3", "4"
                        )
                )
                .build();
        assertThrows(InvalidBannerException.class, ()->updateBannerService.updateBanner(request));

        verify(bannerRepositoryMock).findById("1");
    }
}