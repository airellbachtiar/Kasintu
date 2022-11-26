package com.kasintu.services.inventoryservices.impl;

import com.kasintu.dtos.inventorydtos.InventoryDTO;
import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.repositories.InventoryRepository;
import com.kasintu.services.inventoryservices.GetInventoryService;
import com.kasintu.services.playerservices.GetPlayerService;
import com.kasintu.services.playerservices.PlayerIDValidator;
import com.kasintu.services.playerservices.exception.InvalidPlayerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetInventoryByUserIDServiceImpl implements GetInventoryService
{
    private final InventoryRepository inventoryRepository;
    private final PlayerIDValidator playerIDValidator;
    private final GetPlayerService getPlayerService;

    @Transactional
    @Override
    public Optional<InventoryDTO> getInventory(String userID)
    {
        Optional<PlayerDTO> player = getPlayerService.getPlayer(userID);
        if(player.isEmpty())
        {
            throw new InvalidPlayerException();
        }
        playerIDValidator.validateID(player.get().getPlayerID());
        return inventoryRepository.findByPlayer_PlayerID(player.get().getPlayerID()).map(InventoryDTOConverter::convertToDTO);
    }
}
