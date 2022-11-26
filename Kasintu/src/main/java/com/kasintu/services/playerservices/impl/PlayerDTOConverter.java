package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.repositories.entities.Player;
import lombok.experimental.UtilityClass;

@UtilityClass
final class PlayerDTOConverter {

    public static PlayerDTO convertToDTO(Player player)
    {
        return  PlayerDTO.builder()
                .userID(player.getUser().getUserID())
                .username(player.getUser().getUsername())
                .email(player.getUser().getEmail())
                .playerID(player.getPlayerID())
                .coin(player.getCoin())
                .build();
    }
}
