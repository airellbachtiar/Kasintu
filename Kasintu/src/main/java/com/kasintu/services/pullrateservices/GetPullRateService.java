package com.kasintu.services.pullrateservices;

import com.kasintu.dtos.pullratedtos.PullRateDTO;

import java.util.Optional;

public interface GetPullRateService {
    Optional<PullRateDTO> getPullRate(String pullRateID);
}
