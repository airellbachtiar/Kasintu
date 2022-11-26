package com.kasintu.services.pullrateservices.impl;

import com.kasintu.dtos.pullratedtos.PullRateDTO;
import com.kasintu.repositories.entities.PullRate;
import com.kasintu.services.rarityservices.impl.RarityDTOConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class PullRateDTOConverter {

    public static PullRateDTO convertToDTO(PullRate pullRate) {
        return PullRateDTO.builder()
                .pullRateID(pullRate.getPullRateID())
                .rarity(RarityDTOConverter.convertToDTO(pullRate.getRarity()))
                .rateValue(pullRate.getRateValue())
                .build();
    }
}
