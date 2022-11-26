package com.kasintu.services.bannerservices.impl;

import com.kasintu.dtos.bannerdtos.BannerPullRatesInformationResponseDTO;
import com.kasintu.dtos.pullratedtos.RarityPullRatePercentageDTO;
import com.kasintu.repositories.BannerRepository;
import com.kasintu.repositories.entities.Banner;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.services.bannerservices.BannerPullRatesInformationService;
import com.kasintu.services.bannerservices.exception.InvalidBannerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerPullRatesInformationServiceImpl implements BannerPullRatesInformationService
{

    private final BannerRepository bannerRepository;

    @Transactional
    @Override
    public BannerPullRatesInformationResponseDTO bannerPullRatesInformationService(String bannerID) {

        Optional<Banner> banner = bannerRepository.findById(bannerID);
        if(banner.isEmpty())
        {
            throw new InvalidBannerException();
        }
        Banner checkedBanner = banner.get();

        List<RarityPullRatePercentageDTO> rarityPullRatePercentages = new ArrayList<>();

        double totalRate = checkedBanner.getPullRates().stream().mapToDouble(PullRate::getRateValue).sum();

        for (PullRate pullRate: checkedBanner.getPullRates())
        {
            double ratePercentage = (pullRate.getRateValue()/totalRate)*100;
            String percentage = String.format("%.2f", ratePercentage) + "%";

            rarityPullRatePercentages.add(RarityPullRatePercentageDTO.builder()
                            .rarity(pullRate.getRarity())
                            .percentage(percentage)
                    .build());
        }

        return BannerPullRatesInformationResponseDTO.builder().ratesInformation(rarityPullRatePercentages).build();
    }
}
