package com.kasintu.services.playerservices;

import com.kasintu.dtos.playerdtos.PlayerDTO;

import java.util.Optional;

public interface GetPlayerService {
    Optional<PlayerDTO> getPlayer(String userID);
}
