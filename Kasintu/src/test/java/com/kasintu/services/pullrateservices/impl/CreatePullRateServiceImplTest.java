package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.CreatePullRateRequestDTO;
import com.kasintu.dtos.pullratedtos.CreatePullRateResponseDTO;
import com.kasintu.repositories.PullRateRepository;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.rarityservices.RarityIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePullRateServiceImplTest {

    @Mock
    private PullRateRepository pullRateRepositoryMock;

    @InjectMocks
    private CreatePullRateServiceImpl createPullRateService;

    @Mock
    private RarityIDValidator rarityIDValidator;

    @Captor
    private ArgumentCaptor<PullRate> pullRateCaptor;

    @Test
    void createPullRate()
    {
        PullRate savedPullRate = PullRate.builder()
                .pullRateID("1")
                .rarity(
                        Rarity.builder()
                                .rarityID("1")
                                .build()
                )
                .rateValue(1)
                .build();
        when(pullRateRepositoryMock.save(any())).thenReturn(savedPullRate);

        CreatePullRateRequestDTO request = CreatePullRateRequestDTO.builder()
                .rarityID("1")
                .rateValue(1)
                .build();
        CreatePullRateResponseDTO actualResponse = createPullRateService.createPullRate(request);
        CreatePullRateResponseDTO expectedResponse = CreatePullRateResponseDTO.builder()
                .pullRateID("1")
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(pullRateRepositoryMock).save(pullRateCaptor.capture());
    }
}