package com.kasintu.services.gameservices;

import com.kasintu.dtos.creaturedtos.CreatureDTO;

import java.util.List;

public interface PlayerSummonABannerService {
    List<CreatureDTO> playerSummonABanner(String playerID, String bannerID, Integer summonTimes);
}
