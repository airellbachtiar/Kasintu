package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerCheckRequestDTO;
import com.kasintu.dtos.bannerdtos.BannerCheckResponseDTO;
import com.kasintu.dtos.bannerdtos.UpdateBannerRequestDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.services.bannerservices.BannerCheckService;
import com.kasintu.services.bannerservices.UpdateBannerService;
import com.kasintu.services.bannerservices.exception.InvalidBannerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateBannerServiceImpl implements UpdateBannerService
{

    private final BannerRepository bannerRepository;
    private final BannerCheckService bannerCheckService;

    @Override
    public void updateBanner(UpdateBannerRequestDTO request)
    {
        Optional<Banner> banner = bannerRepository.findById(request.getBannerID());
        if(banner.isEmpty())
        {
            throw new InvalidBannerException();
        }
        Banner updateBanner = banner.get();

        BannerCheckResponseDTO checkedBannerProperties = bannerCheckService.checkBanner(BannerCheckRequestDTO.builder()
                        .pullRateIDs(request.getPullRateIDs())
                .build());

        updateBanner.setCost(request.getCost());
        updateBanner.setPullRates(checkedBannerProperties.getPullRates());

        bannerRepository.save(updateBanner);
    }
}
