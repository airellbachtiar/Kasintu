package com.kasintu.services.bannerservices;

import com.kasintu.dtos.bannerdtos.CreateBannerRequestDTO;
import com.kasintu.dtos.bannerdtos.CreateBannerResponseDTO;

public interface CreateBannerService
{
    CreateBannerResponseDTO createBanner(CreateBannerRequestDTO request);
}
