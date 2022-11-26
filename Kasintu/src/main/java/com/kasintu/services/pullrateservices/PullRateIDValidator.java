package com.kasintu.services.pullrateservices;

import com.kasintu.services.pullrateservices.exception.InvalidPullRateException;

public interface PullRateIDValidator
{
    void validateID(String pullRateID) throws InvalidPullRateException;
}
