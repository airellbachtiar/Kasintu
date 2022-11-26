package com.kasintu.services.pullrateservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPullRateException extends ResponseStatusException {

    public InvalidPullRateException()
    {
        super(HttpStatus.BAD_REQUEST, "PULL_RATE_INVALID");
    }

}
