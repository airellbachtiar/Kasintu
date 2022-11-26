package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerCheckRequestDTO;
import com.kasintu.dtos.bannerdtos.BannerCheckResponseDTO;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.services.bannerservices.BannerCheckService;
import com.kasintu.services.pullrateservices.PullRateIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerCheckServiceImpl implements BannerCheckService
{

    private final PullRateIDValidator pullRateIDValidator;

    @Override
    public BannerCheckResponseDTO checkBanner(BannerCheckRequestDTO request)
    {
        List<PullRate> pullRates = new ArrayList<>();
        for (String pullRateID: request.getPullRateIDs())
        {
            pullRateIDValidator.validateID(pullRateID);
            pullRates.add(PullRate.builder().pullRateID(pullRateID).build());
        }

        return BannerCheckResponseDTO.builder()
                .pullRates(pullRates)
                .build();
    }
}
