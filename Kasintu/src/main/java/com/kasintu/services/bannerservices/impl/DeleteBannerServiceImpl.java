package com.kasintu.services.bannerservices.impl;

import com.kasintu.repositories.BannerRepository;
import com.kasintu.services.bannerservices.DeleteBannerService;
import com.kasintu.services.bannerservices.exception.InvalidBannerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBannerServiceImpl implements DeleteBannerService
{

    private final BannerRepository bannerRepository;

    @Override
    public void deleteBanner(String bannerID)
    {
        if(!bannerRepository.existsById(bannerID))
        {
            throw new InvalidBannerException();
        }
        bannerRepository.deleteById(bannerID);
    }
}
