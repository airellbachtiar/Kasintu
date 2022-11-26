package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.GetAllPlayerResponseDTO;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.services.playerservices.GetAllPlayersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllPlayersServiceImpl implements GetAllPlayersService
{

    private final PlayerRepository playerRepository;

    @Override
    public GetAllPlayerResponseDTO getAllPlayers() {
        return GetAllPlayerResponseDTO.builder()
                .players(playerRepository.findAll().stream().map(PlayerDTOConverter::convertToDTO).toList())
                .build();
    }
}
