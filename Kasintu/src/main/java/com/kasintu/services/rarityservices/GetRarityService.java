package com.kasintu.services.rarityservices;

import com.kasintu.dtos.raritydtos.RarityDTO;

import java.util.Optional;

public interface GetRarityService {
    Optional<RarityDTO> getRarity(String rarityID);
}
