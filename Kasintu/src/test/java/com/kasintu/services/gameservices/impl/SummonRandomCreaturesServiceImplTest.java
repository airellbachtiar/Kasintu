package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.creaturedtos.GetAllCreaturesResponseDTO;
import com.kasintu.dtos.pullratedtos.PullRateDTO;
import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.services.creatureservices.GetAllCreaturesService;
import com.kasintu.services.creatureservices.GetCreatureService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SummonRandomCreaturesServiceImplTest {

    @Mock
    private GetCreatureService getCreatureService;
    @Mock
    private GetAllCreaturesService getAllCreaturesService;

    @InjectMocks
    private SummonRandomCreaturesServiceImpl summonRandomCreaturesService;

    @Test
    void getCreaturesFromBanner()
    {
        CreatureDTO creature = CreatureDTO.builder()
                .creatureID("1")
                .name("creature1")
                .rarity(
                        RarityDTO.builder()
                                .rarityID("1")
                                .build()
                )
                .build();
        List<CreatureDTO> creaturesList = List.of(
                CreatureDTO.builder()
                        .creatureID("1")
                        .name("creature1")
                        .rarity(
                                RarityDTO.builder()
                                        .rarityID("1")
                                        .build()
                        )
                        .build(),
                CreatureDTO.builder()
                        .creatureID("2")
                        .name("creature2")
                        .rarity(
                                RarityDTO.builder()
                                        .rarityID("2")
                                        .build()
                        )
                        .build()
        );
        BannerDTO banner = BannerDTO.builder()
                .bannerID("1")
                .cost(100)
                .pullRates(
                        List.of(
                                PullRateDTO.builder()
                                        .rateValue(2)
                                        .rarity(
                                                RarityDTO.builder()
                                                        .rarityID("1")
                                                        .build()
                                        )
                                        .build(),
                                PullRateDTO.builder()
                                        .rateValue(2)
                                        .rarity(
                                                RarityDTO.builder()
                                                        .rarityID("2")
                                                        .build()
                                        )
                                        .build()
                        )
                )
                .build();

        when(getAllCreaturesService.getAllCreatures(any())).thenReturn(
                GetAllCreaturesResponseDTO.builder()
                        .creatures(creaturesList)
                        .build()
        );
        when(getCreatureService.getCreature("1")).thenReturn(Optional.of(creature));
        when(getCreatureService.getCreature("2")).thenReturn(Optional.empty());

        Integer actualNumber = summonRandomCreaturesService.getCreaturesFromBanner(banner, 20).size();
        assertEquals(20, actualNumber);
    }
}