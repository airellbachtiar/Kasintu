package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.UpdatePullRateRequestDTO;
import com.kasintu.repositories.PullRateRepository;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.pullrateservices.UpdatePullRateService;
import com.kasintu.services.pullrateservices.exception.InvalidPullRateException;
import com.kasintu.services.rarityservices.RarityIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePullRateServiceImpl implements UpdatePullRateService
{

    private final PullRateRepository pullRateRepository;
    private final RarityIDValidator rarityIDValidator;

    @Transactional
    @Override
    public void updatePullRate(UpdatePullRateRequestDTO request) {
        Optional<PullRate> pullRate = pullRateRepository.findById(request.getPullRateID());
        if(pullRate.isEmpty())
        {
            throw new InvalidPullRateException();
        }
        rarityIDValidator.validateID(request.getRarityID());

        PullRate updatePullRate = pullRate.get();

        updatePullRate.setRarity(Rarity.builder().rarityID(request.getRarityID()).build());
        updatePullRate.setRateValue(request.getRateValue());

        pullRateRepository.save(updatePullRate);
    }
}
