package com.kasintu.services.pullrateservices.impl;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPullRateServiceImplTest {

    @Mock
    private PullRateRepository pullRateRepositoryMock;

    @InjectMocks
    private GetPullRateServiceImpl getPullRateService;

    @Test
    void getPullRate() {
        PullRate pullRate = PullRate.builder()
                .pullRateID("1")
                .rarity(Rarity.builder()
                        .rarityID("1")
                        .build())
                .rateValue(1)
                .build();
        when(pullRateRepositoryMock.findById("1")).thenReturn(Optional.of(pullRate));

        Optional<PullRateDTO> pullRateOptional = getPullRateService.getPullRate("1");
        PullRateDTO actualResult = pullRateOptional.orElseThrow();
        PullRateDTO expectedResult = PullRateDTO.builder()
                .pullRateID("1")
                .rarity(RarityDTO.builder()
                        .rarityID("1")
                        .build())
                .rateValue(1)
                .build();

        assertEquals(expectedResult, actualResult);
        verify(pullRateRepositoryMock).findById("1");
    }
}