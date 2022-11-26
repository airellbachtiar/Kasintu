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
class DeleteBannerServiceImplTest {

    @Mock
    private BannerRepository bannerRepositoryMock;

    @InjectMocks
    private DeleteBannerServiceImpl deleteBannerService;

    @Test
    void deleteBanner_Successful()
    {
        String id = "1";

        when(bannerRepositoryMock.existsById(id)).thenReturn(true);
        deleteBannerService.deleteBanner(id);

        verify(bannerRepositoryMock).deleteById(id);
        verify(bannerRepositoryMock).existsById(id);
    }

    @Test
    void deleteBanner_IDNotFound_ThrowException()
    {
        String id = "1";

        when(bannerRepositoryMock.existsById(id)).thenReturn(false);

        assertThrows(InvalidBannerException.class, ()->deleteBannerService.deleteBanner(id));
    }
}