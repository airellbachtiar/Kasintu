package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.UpdatePullRateRequestDTO;
import com.kasintu.repositories.PullRateRepository;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.pullrateservices.exception.InvalidPullRateException;
import com.kasintu.services.rarityservices.RarityIDValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdatePullRateServiceImplTest {

    @Mock
    private PullRateRepository pullRateRepositoryMock;

    @InjectMocks
    private UpdatePullRateServiceImpl updatePullRateService;

    @Mock
    private RarityIDValidator rarityIDValidator;

    @Test
    void updatePullRate_Successful()
    {
        PullRate pullRateBeforeUpdate = PullRate.builder()
                .pullRateID("1")
                .rarity(Rarity.builder()
                        .rarityID("1")
                        .build())
                .rateValue(1)
                .build();
        when(pullRateRepositoryMock.findById("1")).thenReturn(Optional.of(pullRateBeforeUpdate));

        UpdatePullRateRequestDTO request = UpdatePullRateRequestDTO.builder()
                .pullRateID("1")
                .rarityID("2")
                .rateValue(2)
                .build();
        updatePullRateService.updatePullRate(request);

        PullRate expectedSavedPullRate = PullRate.builder()
                .pullRateID("1")
                .rarity(Rarity.builder()
                        .rarityID("2")
                        .build())
                .rateValue(2)
                .build();

        verify(pullRateRepositoryMock).findById("1");
        verify(pullRateRepositoryMock).save(expectedSavedPullRate);
    }

    @Test
    void UpdatePullRate_PullRateNotFound_ThrowException()
    {
        when(pullRateRepositoryMock.findById("1")).thenReturn(Optional.empty());

        UpdatePullRateRequestDTO request = UpdatePullRateRequestDTO.builder()
                .pullRateID("1")
                .rarityID("2")
                .rateValue(2)
                .build();
        assertThrows(InvalidPullRateException.class, () -> updatePullRateService.updatePullRate(request));

        verify(pullRateRepositoryMock).findById("1");
        verifyNoInteractions(rarityIDValidator);
    }
}