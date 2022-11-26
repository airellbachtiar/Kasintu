package com.kasintu.services.bannerservices;

import com.kasintu.dtos.bannerdtos.BannerCheckRequestDTO;
import com.kasintu.dtos.bannerdtos.BannerCheckResponseDTO;

public interface BannerCheckService
{
    BannerCheckResponseDTO checkBanner(BannerCheckRequestDTO request);
}
