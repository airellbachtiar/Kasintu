package com.kasintu.services.bannerservices.impl;

import com.kasintu.repositories.BannerRepository;
import com.kasintu.services.bannerservices.BannerIDValidator;
import com.kasintu.services.bannerservices.exception.InvalidBannerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BannerIDValidatorImpl implements BannerIDValidator
{

    private final BannerRepository bannerRepository;

    @Override
    public void validateID(String bannerID)
    {
        if(bannerID == null || !bannerRepository.existsById(bannerID))
        {
            throw new InvalidBannerException();
        }
    }
}
