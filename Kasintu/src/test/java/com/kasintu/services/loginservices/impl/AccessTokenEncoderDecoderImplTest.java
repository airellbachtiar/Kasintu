package com.kasintu.services.loginservices.impl;

import com.kasintu.dtos.logindtos.AccessTokenDTO;
import com.kasintu.services.loginservices.exception.InvalidAccessTokenException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenEncoderDecoderImplTest {

    private final AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl("123HIOFNEOHF9NY893CYFNO22C3289YR8932092CNY32Y3892C8902");

    @Test
    void encode() {
        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .userID("123")
                .build();

        String accessToken = accessTokenEncoderDecoder.encode(accessTokenDTO);
        assertNotNull(accessToken);
    }

    @Test
    void decode()
    {
        AccessTokenDTO expectedAccessToken = AccessTokenDTO.builder()
                .userID("123")
                .roles(List.of("PLAYER"))
                .build();

        String accessToken = accessTokenEncoderDecoder.encode(expectedAccessToken);
        AccessTokenDTO actualAccessToken = accessTokenEncoderDecoder.decode(accessToken);
        assertEquals(expectedAccessToken, actualAccessToken);
    }

    @Test
    void decode_ReturnException()
    {
        assertThrows(InvalidAccessTokenException.class, ()-> accessTokenEncoderDecoder.decode("accessToken.sadasd.asdasda"));
    }
}