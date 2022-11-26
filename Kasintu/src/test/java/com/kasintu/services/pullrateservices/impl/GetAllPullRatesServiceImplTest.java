package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.GetAllPullRatesResponseDTO;
import com.kasintu.dtos.pullratedtos.PullRateDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.PullRateRepository;
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
class GetAllPullRatesServiceImplTest {

    @Mock
    private PullRateRepository pullRateRepositoryMock;

    @InjectMocks
    private GetAllPullRatesServiceImpl getAllPullRatesService;

    @Test
    void getAllPullRates()
    {
        List<PullRate> pullRates = List.of(
                PullRate.builder()
                        .pullRateID("1")
                        .rarity(
                                Rarity.builder()
                                        .rarityID("1")
                                        .build()
                        )
                        .rateValue(1)
                        .build(),
                PullRate.builder()
                        .pullRateID("2")
                        .rarity(
                                Rarity.builder()
                                        .rarityID("2")
                                        .build()
                        )
                        .rateValue(2)
                        .build()
        );
        when(pullRateRepositoryMock.findAll()).thenReturn(pullRates);

        GetAllPullRatesResponseDTO actualResponse = getAllPullRatesService.getAllPullRates();
        GetAllPullRatesResponseDTO expectedResponse = GetAllPullRatesResponseDTO.builder()
                .pullRates(
                        List.of(
                                PullRateDTO.builder()
                                        .pullRateID("1")
                                        .rarity(
                                                RarityDTO.builder()
                                                        .rarityID("1")
                                                        .build()
                                        )
                                        .rateValue(1)
                                        .build(),
                                PullRateDTO.builder()
                                        .pullRateID("2")
                                        .rarity(
                                                RarityDTO.builder()
                                                        .rarityID("2")
                                                        .build()
                                        )
                                        .rateValue(2)
                                        .build()
                        )
                )
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(pullRateRepositoryMock).findAll();
    }
}