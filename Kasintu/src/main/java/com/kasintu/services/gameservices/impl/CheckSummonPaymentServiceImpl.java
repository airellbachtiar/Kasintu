package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.dtos.playerdtos.UpdatePlayerRequestDTO;
import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.services.gameservices.CheckSummonPaymentService;
import com.kasintu.services.playerservices.UpdatePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CheckSummonPaymentServiceImpl implements CheckSummonPaymentService {

    private final UpdatePlayerService updatePlayerService;

    @Transactional
    @Override
    public boolean checkSummonPayment(UserDTO user, BannerDTO banner, int summonTimes) {

        if(banner != null
                && user instanceof PlayerDTO player
                && player.getCoin() >= banner.getCost() * summonTimes)
        {
            UpdatePlayerRequestDTO request = UpdatePlayerRequestDTO.builder().playerID(player.getPlayerID()).coin((player.getCoin() - (banner.getCost() * summonTimes))).build();
            updatePlayerService.updatePlayer(request);
            return true;
        }
        return false;
    }
}
