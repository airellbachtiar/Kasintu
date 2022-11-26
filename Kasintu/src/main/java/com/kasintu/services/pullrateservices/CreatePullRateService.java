package com.kasintu.services.pullrateservices;

import com.kasintu.dtos.pullratedtos.CreatePullRateRequestDTO;
import com.kasintu.dtos.pullratedtos.CreatePullRateResponseDTO;

public interface CreatePullRateService {
    CreatePullRateResponseDTO createPullRate(CreatePullRateRequestDTO request);
}
