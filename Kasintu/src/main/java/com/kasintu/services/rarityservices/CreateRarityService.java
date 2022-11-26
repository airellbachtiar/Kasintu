package com.kasintu.services.rarityservices;

import com.kasintu.dtos.raritydtos.CreateRarityRequestDTO;
import com.kasintu.dtos.raritydtos.CreateRarityResponseDTO;

public interface CreateRarityService {
    CreateRarityResponseDTO createRarity(CreateRarityRequestDTO request);
}
