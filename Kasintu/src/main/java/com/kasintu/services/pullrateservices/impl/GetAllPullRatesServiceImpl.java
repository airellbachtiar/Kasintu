package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.GetAllPullRatesResponseDTO;
import com.kasintu.repositories.PullRateRepository;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.services.pullrateservices.GetAllPullRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPullRatesServiceImpl implements GetAllPullRatesService {

    private final PullRateRepository pullRateRepository;

    @Override
    public GetAllPullRatesResponseDTO getAllPullRates() {
        List<PullRate> result = pullRateRepository.findAll();

        final GetAllPullRatesResponseDTO response = new GetAllPullRatesResponseDTO();
        response.setPullRates(result.stream().map(PullRateDTOConverter::convertToDTO).toList());
        return response;
    }
}
