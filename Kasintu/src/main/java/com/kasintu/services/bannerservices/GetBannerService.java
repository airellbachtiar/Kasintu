package com.kasintu.services.bannerservices;

import com.kasintu.dtos.bannerdtos.BannerDTO;

import java.util.Optional;

public interface GetBannerService
{
    Optional<BannerDTO> getBanner(String bannerID);
}
