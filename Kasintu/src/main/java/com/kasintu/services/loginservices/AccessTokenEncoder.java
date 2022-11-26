package com.kasintu.services.loginservices;

import com.kasintu.dtos.logindtos.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessTokenDTO);
}
