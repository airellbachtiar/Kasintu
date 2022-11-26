package com.kasintu.services.bannerservices;

import com.kasintu.services.bannerservices.exception.InvalidBannerException;

public interface BannerIDValidator {
    void validateID(String bannerID) throws InvalidBannerException;
}
