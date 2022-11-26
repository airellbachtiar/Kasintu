package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.services.pullrateservices.impl.PullRateDTOConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class BannerDTOConverter
{

    public static BannerDTO convertToDTO(Banner banner)
    {
        return BannerDTO.builder()
                .bannerID(banner.getBannerID())
                .cost(banner.getCost())
                .pullRates(banner.getPullRates().stream().map(PullRateDTOConverter::convertToDTO).toList())
                .build();
    }
}
