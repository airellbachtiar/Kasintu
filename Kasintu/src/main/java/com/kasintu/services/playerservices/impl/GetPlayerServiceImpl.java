package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.services.playerservices.GetPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetPlayerServiceImpl implements GetPlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public Optional<PlayerDTO> getPlayer(String userID) {
        return Optional.ofNullable(PlayerDTOConverter.convertToDTO(playerRepository.findByUser_UserID(userID)));
    }
}
