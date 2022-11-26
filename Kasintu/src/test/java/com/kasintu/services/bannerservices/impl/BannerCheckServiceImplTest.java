package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerCheckRequestDTO;
import com.kasintu.dtos.bannerdtos.BannerCheckResponseDTO;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.services.pullrateservices.PullRateIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BannerCheckServiceImplTest {

    @Mock
    private PullRateIDValidator pullRateIDValidator;

    @InjectMocks
    private BannerCheckServiceImpl bannerCheckService;

    @Test
    void checkBanner()
    {
        BannerCheckRequestDTO request = BannerCheckRequestDTO.builder()
                .pullRateIDs(
                        List.of(
                                "1", "2", "3"
                        )
                )
                .build();

        BannerCheckResponseDTO actualResponse = bannerCheckService.checkBanner(request);
        BannerCheckResponseDTO expectedResponse = BannerCheckResponseDTO.builder()
                .pullRates(
                        List.of(
                                PullRate.builder()
                                        .pullRateID("1")
                                        .build(),
                                PullRate.builder()
                                        .pullRateID("2")
                                        .build(),
                                PullRate.builder()
                                        .pullRateID("3")
                                        .build()
                        )
                )
                .build();

        assertEquals(expectedResponse, actualResponse);
    }
}