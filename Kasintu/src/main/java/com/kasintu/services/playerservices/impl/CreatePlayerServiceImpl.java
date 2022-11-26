package com.kasintu.services.playerservices.impl;

import com.kasintu.dtos.playerdtos.CreatePlayerRequestDTO;
import com.kasintu.dtos.playerdtos.CreatePlayerResponseDTO;
import com.kasintu.dtos.userdtos.CreateUserRequestDTO;
import com.kasintu.repositories.InventoryRepository;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.entities.Inventory;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.playerservices.CreatePlayerService;
import com.kasintu.services.userservices.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePlayerServiceImpl implements CreatePlayerService {

    private final PlayerRepository playerRepository;
    private final InventoryRepository inventoryRepository;
    private final CreateUserService createUserService;

    @Transactional
    @Override
    public CreatePlayerResponseDTO createPlayer(CreatePlayerRequestDTO request) {

        User savedUser = createUserService.createUser(
                CreateUserRequestDTO.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .username(request.getUsername())
                .build());

        String playerID = UUID.randomUUID().toString().replace("-", "");

        Player newPlayer = Player.builder()
                .playerID(playerID)
                .user(savedUser)
                .coin(10000)
                .build();
        Player savedPlayer = playerRepository.save(newPlayer);

        saveNewInventory(savedPlayer);

        return CreatePlayerResponseDTO.builder().playerID(savedPlayer.getPlayerID()).build();
    }

    private void saveNewInventory(Player savedPlayer)
    {
        String inventoryID = UUID.randomUUID().toString().replace("-", "");

         inventoryRepository.save(
                Inventory.builder()
                        .inventoryID(inventoryID)
                        .player(savedPlayer)
                        .build()
        );
    }
}
