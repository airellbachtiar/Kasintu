package com.kasintu.services.pullrateservices.impl;

import com.kasintu.repositories.PullRateRepository;
import com.kasintu.services.pullrateservices.DeletePullRateService;
import com.kasintu.services.pullrateservices.exception.InvalidPullRateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePullRateServiceImpl implements DeletePullRateService {

    private final PullRateRepository pullRateRepository;

    @Override
    @Transactional
    public void deletePullRate(String pullRateID)
    {
        if(!pullRateRepository.existsById(pullRateID))
        {
            throw new InvalidPullRateException();
        }
        pullRateRepository.deleteById(pullRateID);
    }
}
