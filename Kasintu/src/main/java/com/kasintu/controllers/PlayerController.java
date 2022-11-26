package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.playerdtos.*;
import com.kasintu.services.playerservices.CreatePlayerService;
import com.kasintu.services.playerservices.GetAllPlayersService;
import com.kasintu.services.playerservices.GetPlayerService;
import com.kasintu.services.playerservices.UpdatePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PlayerController {

    private final CreatePlayerService createPlayerService;
    private final GetAllPlayersService getAllPlayersService;
    private final GetPlayerService getPlayerService;
    private final UpdatePlayerService updatePlayerService;

    @GetMapping
    public ResponseEntity<GetAllPlayerResponseDTO> getAllPlayers()
    {
        return ResponseEntity.ok().body(getAllPlayersService.getAllPlayers());
    }

    @GetMapping("{userID}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable String userID)
    {
        Optional<PlayerDTO> playerDTO = getPlayerService.getPlayer(userID);
        if(playerDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(playerDTO.get());
    }

    @PostMapping
    public ResponseEntity<CreatePlayerResponseDTO> createPlayer(@RequestBody @Valid CreatePlayerRequestDTO request)
    {
        CreatePlayerResponseDTO response = createPlayerService.createPlayer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{playerID}")
    @IsAuthenticated
    public ResponseEntity<Void> updatePlayer(@PathVariable("playerID") String playerID, @RequestBody @Valid UpdatePlayerRequestDTO request)
    {
        request.setPlayerID(playerID);
        updatePlayerService.updatePlayer(request);
        return ResponseEntity.noContent().build();
    }
}
