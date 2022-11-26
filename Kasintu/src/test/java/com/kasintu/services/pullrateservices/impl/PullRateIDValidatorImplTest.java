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
class PullRateIDValidatorImplTest {

    @Mock
    private PullRateRepository pullRateRepositoryMock;

    @InjectMocks
    private PullRateIDValidatorImpl pullRateIDValidator;

    @Test
    void validateID_Successful()
    {
        String pullRateID = "1";

        when(pullRateRepositoryMock.existsById(pullRateID)).thenReturn(true);
        pullRateIDValidator.validateID(pullRateID);

        verify(pullRateRepositoryMock).existsById(pullRateID);
    }

    @Test
    void validateID_IDNotFound_ThrowException()
    {
        String pullRateID = "1";

        when(pullRateRepositoryMock.existsById(pullRateID)).thenReturn(false);
        assertThrows(InvalidPullRateException.class, ()->pullRateIDValidator.validateID(pullRateID));
    }

    @Test
    void validateID_IDNull_ThrowException()
    {
        assertThrows(InvalidPullRateException.class, ()->pullRateIDValidator.validateID(null));
    }
}