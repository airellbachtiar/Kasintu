package com.kasintu.services.bannerservices.impl;

import com.kasintu.repositories.BannerRepository;
import com.kasintu.services.bannerservices.exception.InvalidBannerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BannerIDValidatorImplTest {

    @Mock
    private BannerRepository bannerRepositoryMock;

    @InjectMocks
    private BannerIDValidatorImpl bannerIDValidator;

    @Test
    void validateID_Successful()
    {
        String bannerID = "1";

        when(bannerRepositoryMock.existsById(bannerID)).thenReturn(true);
        bannerIDValidator.validateID(bannerID);

        verify(bannerRepositoryMock).existsById(bannerID);
    }

    @Test
    void validateID_BannerNotFound_ThrowException()
    {
        String bannerID = "1";

        when(bannerRepositoryMock.existsById(bannerID)).thenReturn(false);

        assertThrows(InvalidBannerException.class, ()->bannerIDValidator.validateID(bannerID));
    }
}