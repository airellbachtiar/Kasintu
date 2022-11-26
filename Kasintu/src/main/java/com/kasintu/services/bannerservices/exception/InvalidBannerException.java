package com.kasintu.services.bannerservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidBannerException extends ResponseStatusException
{
    public InvalidBannerException()
    {
        super(HttpStatus.BAD_REQUEST, "BANNER_INVALID");
    }
}
