package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.bannerdtos.GetSummonFromBannerRequestDTO;
import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.services.gameservices.PlayerSummonABannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class GameController {

    private final PlayerSummonABannerService playerSummonABannerService;

    @IsAuthenticated
    @PostMapping("{userID}")
    public ResponseEntity<List<CreatureDTO>> summonCreatures(@PathVariable String userID, @RequestBody GetSummonFromBannerRequestDTO getSummonFromBannerRequestDTO)
    {
        return ResponseEntity.ok().body(playerSummonABannerService.playerSummonABanner(userID, getSummonFromBannerRequestDTO.getBannerID(), getSummonFromBannerRequestDTO.getSummonTimes()));
    }
}
