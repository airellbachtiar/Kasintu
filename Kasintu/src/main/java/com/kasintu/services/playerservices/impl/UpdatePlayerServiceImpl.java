package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.UpdatePlayerRequestDTO;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.entities.Player;
import com.kasintu.services.playerservices.UpdatePlayerService;
import com.kasintu.services.playerservices.exception.InvalidPlayerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePlayerServiceImpl implements UpdatePlayerService
{

    private final PlayerRepository playerRepository;

    @Transactional
    @Override
    public void updatePlayer(UpdatePlayerRequestDTO request)
    {
        Optional<Player> player = playerRepository.findById(request.getPlayerID());
        if(player.isEmpty())
        {
            throw new InvalidPlayerException();
        }
        Player updatePlayer = player.get();

        updatePlayer.setCoin(request.getCoin());

        playerRepository.save(updatePlayer);
    }
}
