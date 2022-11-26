package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.services.bannerservices.GetBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetBannerServiceImpl implements GetBannerService
{

    private final BannerRepository bannerRepository;

    @Override
    public Optional<BannerDTO> getBanner(String bannerID) {
        return bannerRepository.findById(bannerID).map(BannerDTOConverter::convertToDTO);
    }
}
