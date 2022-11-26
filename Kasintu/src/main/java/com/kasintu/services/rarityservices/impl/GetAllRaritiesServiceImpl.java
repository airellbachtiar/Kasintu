package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.GetAllRaritiesResponseDTO;
import com.kasintu.repositories.RarityRepository;
import com.kasintu.services.rarityservices.GetAllRaritiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllRaritiesServiceImpl implements GetAllRaritiesService {

    private final RarityRepository rarityRepository;

    @Override
    public GetAllRaritiesResponseDTO getAllRarities() {
        return GetAllRaritiesResponseDTO.builder().rarities(rarityRepository.findAll().stream().map(RarityDTOConverter::convertToDTO).toList()).build();
    }
}
