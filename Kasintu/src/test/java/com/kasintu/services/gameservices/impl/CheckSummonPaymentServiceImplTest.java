package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.services.playerservices.UpdatePlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckSummonPaymentServiceImplTest {

    @Mock
    private UpdatePlayerService updatePlayerService;

    @InjectMocks
    private CheckSummonPaymentServiceImpl checkSummonPaymentService;

    @Test
    void checkSummonPayment_ReturnTrue()
    {
        PlayerDTO player = PlayerDTO.builder()
                .userID("1")
                .email("user@email.com")
                .username("username")
                .playerID("2")
                .coin(1000)
                .build();
        BannerDTO banner = BannerDTO.builder()
                .pullRates(List.of())
                .cost(100)
                .bannerID("1")
                .build();

        assertTrue(checkSummonPaymentService.checkSummonPayment(player, banner, 2));
    }

    @Test
    void checkSummonPayment_ReturnFalse()
    {
        UserDTO user = UserDTO.builder()
                .userID("1")
                .email("user@email.com")
                .username("username")
                .build();
        assertFalse(checkSummonPaymentService.checkSummonPayment(user, null, 1));
    }
}