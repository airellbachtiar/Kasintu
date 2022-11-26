package com.kasintu.services.pullrateservices.impl;

import com.kasintu.repositories.PullRateRepository;
import com.kasintu.services.pullrateservices.exception.InvalidPullRateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeletePullRateServiceImplTest {

    @Mock
    private PullRateRepository pullRateRepository;

    @InjectMocks
    private DeletePullRateServiceImpl deletePullRateService;

    @Test
    void deletePullRate_Successful()
    {
        String id = "1";

        when(pullRateRepository.existsById(id)).thenReturn(true);
        deletePullRateService.deletePullRate(id);

        verify(pullRateRepository).deleteById(id);
        verify(pullRateRepository).existsById(id);
    }

    @Test
    void deletePullRate_IDNotFound_ThrowException()
    {
        String id = "1";

        when(pullRateRepository.existsById(id)).thenReturn(false);

        assertThrows(InvalidPullRateException.class, ()->deletePullRateService.deletePullRate(id));
    }
}