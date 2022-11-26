package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.raritydtos.*;
import com.kasintu.services.rarityservices.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rarities")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class RarityController {

    private final CreateRarityService createRarityService;
    private final DeleteRarityService deleteRarityService;
    private final GetAllRaritiesService getAllRaritiesService;
    private final GetRarityService getRarityService;
    private final UpdateRarityService updateRarityService;

    @GetMapping
    public ResponseEntity<GetAllRaritiesResponseDTO> getAllRarities()
    {
        return ResponseEntity.ok().body(getAllRaritiesService.getAllRarities());
    }

    @GetMapping("{rarityID}")
    public ResponseEntity<RarityDTO> getRarity(@PathVariable String rarityID)
    {
        Optional<RarityDTO> rarityDTO = getRarityService.getRarity(rarityID);
        if(rarityDTO.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(rarityDTO.get());
    }

    @IsAuthenticated
    @PostMapping()
    public ResponseEntity<CreateRarityResponseDTO> createRarity(@RequestBody CreateRarityRequestDTO request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(createRarityService.createRarity(request));
    }

    @IsAuthenticated
    @DeleteMapping("{rarityID}")
    public ResponseEntity<Void> deleteRarity(@PathVariable String rarityID)
    {
        deleteRarityService.deleteRarity(rarityID);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @PutMapping("{rarityID}")
    public ResponseEntity<Void> updateCreature(@PathVariable String rarityID, @RequestBody UpdateRarityRequestDTO request)
    {
        request.setRarityID(rarityID);
        updateRarityService.updateRarity(request);
        return ResponseEntity.noContent().build();
    }
}
