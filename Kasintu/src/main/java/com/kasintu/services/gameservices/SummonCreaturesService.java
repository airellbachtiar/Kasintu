package com.kasintu.services.gameservices;

import com.kasintu.dtos.bannerdtos.BannerDTO;
import com.kasintu.dtos.creaturedtos.CreatureDTO;

import java.util.List;

public interface SummonCreaturesService {
    List<CreatureDTO> getCreaturesFromBanner(BannerDTO banner, int times);
}
