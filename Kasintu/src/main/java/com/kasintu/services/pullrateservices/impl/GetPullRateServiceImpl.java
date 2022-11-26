package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.PullRateDTO;
import com.kasintu.repositories.PullRateRepository;
import com.kasintu.services.pullrateservices.GetPullRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetPullRateServiceImpl implements GetPullRateService
{

    private final PullRateRepository pullRateRepository;

    @Override
    public Optional<PullRateDTO> getPullRate(String pullRateID)
    {
        return pullRateRepository.findById(pullRateID).map(PullRateDTOConverter::convertToDTO);
    }
}
