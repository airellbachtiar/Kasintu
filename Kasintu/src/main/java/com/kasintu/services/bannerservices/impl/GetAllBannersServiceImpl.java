package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.GetAllBannersResponseDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.services.bannerservices.GetAllBannersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllBannersServiceImpl implements GetAllBannersService
{

    private final BannerRepository bannerRepository;

    @Transactional
    @Override
    public GetAllBannersResponseDTO getAllBanners() {
        List<Banner> result = bannerRepository.findAll();

        return GetAllBannersResponseDTO.builder()
                .banners(result.stream().map(BannerDTOConverter::convertToDTO).toList())
                .build();
    }
}
