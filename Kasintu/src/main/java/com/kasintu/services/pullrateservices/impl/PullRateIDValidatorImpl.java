package com.kasintu.services.pullrateservices.impl;

import com.kasintu.repositories.PullRateRepository;
import com.kasintu.services.pullrateservices.PullRateIDValidator;
import com.kasintu.services.pullrateservices.exception.InvalidPullRateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PullRateIDValidatorImpl implements PullRateIDValidator
{

    private final PullRateRepository pullRateRepository;

    @Override
    public void validateID(String pullRateID)
    {
        if(!pullRateRepository.existsById(pullRateID))
        {
            throw new InvalidPullRateException();
        }
    }
}
