package com.kasintu.services.playerservices;

import com.kasintu.dtos.playerdtos.CreatePlayerRequestDTO;
import com.kasintu.dtos.playerdtos.CreatePlayerResponseDTO;

public interface CreatePlayerService {
    CreatePlayerResponseDTO createPlayer(CreatePlayerRequestDTO request);
}
