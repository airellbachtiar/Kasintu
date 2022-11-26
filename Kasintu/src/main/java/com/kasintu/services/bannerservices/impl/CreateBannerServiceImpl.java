package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerCheckRequestDTO;
import com.kasintu.dtos.bannerdtos.BannerCheckResponseDTO;
import com.kasintu.dtos.bannerdtos.CreateBannerRequestDTO;
import com.kasintu.dtos.bannerdtos.CreateBannerResponseDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.services.bannerservices.BannerCheckService;
import com.kasintu.services.bannerservices.CreateBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateBannerServiceImpl implements CreateBannerService
{

    private final BannerRepository bannerRepository;
    private final BannerCheckService bannerCheckService;

    @Override
    public CreateBannerResponseDTO createBanner(CreateBannerRequestDTO request)
    {
        BannerCheckResponseDTO checkedBannerProperties = bannerCheckService.checkBanner(BannerCheckRequestDTO.builder()
                .pullRateIDs(request.getPullRateIDs())
                .build());

        String bannerID = UUID.randomUUID().toString().replace("-", "");

        Banner newBanner = Banner.builder()
                .bannerID(bannerID)
                .cost(request.getCost())
                .pullRates(checkedBannerProperties.getPullRates())
                .build();

        Banner savedBanner = bannerRepository.save(newBanner);

        return CreateBannerResponseDTO.builder().bannerID(savedBanner.getBannerID()).build();
    }
}
