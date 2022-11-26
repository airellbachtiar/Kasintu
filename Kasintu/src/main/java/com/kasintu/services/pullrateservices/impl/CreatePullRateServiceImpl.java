package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.CreatePullRateRequestDTO;
import com.kasintu.dtos.pullratedtos.CreatePullRateResponseDTO;
import com.kasintu.repositories.PullRateRepository;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.pullrateservices.CreatePullRateService;
import com.kasintu.services.rarityservices.RarityIDValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePullRateServiceImpl implements CreatePullRateService {

    private final PullRateRepository pullRateRepository;
    private final RarityIDValidator rarityIDValidator;

    @Transactional
    @Override
    public CreatePullRateResponseDTO createPullRate(CreatePullRateRequestDTO request)
    {
        rarityIDValidator.validateID(request.getRarityID());

        String pullRateID = UUID.randomUUID().toString().replace("-", "");

        PullRate newPullRate = PullRate.builder()
                .pullRateID(pullRateID)
                .rarity(Rarity.builder().rarityID(request.getRarityID()).build())
                .rateValue(request.getRateValue()).build();
        PullRate savedPullRate = pullRateRepository.save(newPullRate);

        return CreatePullRateResponseDTO.builder().pullRateID(savedPullRate.getPullRateID()).build();
    }
}
