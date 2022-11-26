package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.services.bannerservices.GetBannerService;
import com.kasintu.services.gameservices.CheckSummonPaymentService;
import com.kasintu.services.gameservices.SaveCreatureToInventoryService;
import com.kasintu.services.gameservices.SummonCreaturesService;
import com.kasintu.services.playerservices.GetPlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerSummonABannerServiceImplTest {

    @Mock
    private GetBannerService getBannerService;
    @Mock
    private GetPlayerService getPlayerService;
    @Mock
    private SummonCreaturesService summonCreaturesService;
    @Mock
    private SaveCreatureToInventoryService saveCreatureToInventoryService;
    @Mock
    private CheckSummonPaymentService checkSummonPaymentService;

    @InjectMocks
    private PlayerSummonABannerServiceImpl playerSummonABannerService;

    @Test
    void playerSummonABanner_ReturnListOfCreatures()
    {
        BannerDTO banner = BannerDTO.builder()
                .bannerID("1")
                .pullRates(List.of())
                .cost(100)
                .build();
        PlayerDTO player = PlayerDTO.builder()
                .userID("1")
                .email("user@email.com")
                .username("username")
                .playerID("2")
                .coin(1000)
                .build();
        List<CreatureDTO> obtainedCreatures = List.of(
                CreatureDTO.builder().creatureID("1").build(),
                CreatureDTO.builder().creatureID("2").build(),
                CreatureDTO.builder().creatureID("3").build()
        );
        when(getBannerService.getBanner("1")).thenReturn(Optional.of(banner));
        when(getPlayerService.getPlayer("1")).thenReturn(Optional.of(player));
        when(checkSummonPaymentService.checkSummonPayment(player, banner, 1)).thenReturn(true);
        when(summonCreaturesService.getCreaturesFromBanner(banner, 1)).thenReturn(obtainedCreatures);
        when(saveCreatureToInventoryService.saveCreatureToInventory(obtainedCreatures, player)).thenReturn(true);

        List<CreatureDTO> actualResult = playerSummonABannerService.playerSummonABanner("1", "1", null);
        assertEquals(obtainedCreatures, actualResult);
        verify(getBannerService).getBanner("1");
        verify(getPlayerService).getPlayer("1");
        verify(checkSummonPaymentService).checkSummonPayment(player, banner, 1);
        verify(summonCreaturesService).getCreaturesFromBanner(banner, 1);
    }

    @Test
    void playerSummonABanner_Failed_ReturnEmptyArray()
    {
        when(getBannerService.getBanner("1")).thenReturn(Optional.empty());
        List<CreatureDTO> actualResult = playerSummonABannerService.playerSummonABanner("1", "1", null);
        List<CreatureDTO> expectedResult = new ArrayList<>();
        assertEquals(expectedResult, actualResult);
    }
}