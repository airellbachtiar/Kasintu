package com.kasintu.services.gameservices.impl;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.playerdtos.PlayerDTO;
import com.kasintu.services.bannerservices.GetBannerService;
import com.kasintu.services.gameservices.CheckSummonPaymentService;
import com.kasintu.services.gameservices.PlayerSummonABannerService;
import com.kasintu.services.gameservices.SaveCreatureToInventoryService;
import com.kasintu.services.gameservices.SummonCreaturesService;
import com.kasintu.services.playerservices.GetPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerSummonABannerServiceImpl implements PlayerSummonABannerService {

    private final GetBannerService getBannerService;
    private final GetPlayerService getPlayerService;
    private final SummonCreaturesService summonCreaturesService;
    private final SaveCreatureToInventoryService saveCreatureToInventoryService;
    private final CheckSummonPaymentService checkSummonPaymentService;

    @Transactional
    @Override
    public List<CreatureDTO> playerSummonABanner(String userID, String bannerID, Integer summonTimes)
    {
        Optional<BannerDTO> banner = getBannerService.getBanner(bannerID);
        Optional<PlayerDTO> user = getPlayerService.getPlayer(userID);

        if(summonTimes == null)
        {
            summonTimes = 1;
        }

        if(user.isPresent() && banner.isPresent() && checkSummonPaymentService.checkSummonPayment(user.get(), banner.get(), summonTimes))
        {
            List<CreatureDTO> obtainedCreatures = summonCreaturesService.getCreaturesFromBanner(banner.get(), summonTimes);
            if(saveCreatureToInventoryService.saveCreatureToInventory(obtainedCreatures, user.get()))
            {
                return obtainedCreatures;
            }
        }
        return new ArrayList<>();
    }
}
