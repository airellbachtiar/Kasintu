package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.RarityDTO;
import com.kasintu.repositories.RarityRepository;
import com.kasintu.services.rarityservices.GetRarityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetRarityServiceImpl implements GetRarityService {

    private final RarityRepository rarityRepository;

    @Override
    public Optional<RarityDTO> getRarity(String rarityID)
    {
        return rarityRepository.findById(rarityID).map(RarityDTOConverter::convertToDTO);
    }
}
