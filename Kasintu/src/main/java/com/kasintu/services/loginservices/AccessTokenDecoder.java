package com.kasintu.services.loginservices;

import com.kasintu.dtos.logindtos.AccessTokenDTO;

public interface AccessTokenDecoder {
    AccessTokenDTO decode(String accessTokenEncoded);
}
