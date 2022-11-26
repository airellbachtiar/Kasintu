package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.CreateRarityRequestDTO;
import com.kasintu.dtos.raritydtos.CreateRarityResponseDTO;
import com.kasintu.repositories.RarityRepository;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.rarityservices.CreateRarityService;
import com.kasintu.services.rarityservices.exception.InvalidRarityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateRarityServiceImpl implements CreateRarityService {

    private final RarityRepository rarityRepository;

    @Transactional
    @Override
    public CreateRarityResponseDTO createRarity(CreateRarityRequestDTO request) {

        if(rarityRepository.existsByRarityType(request.getRarityType()) || !StringUtils.hasText(request.getRarityType()))
        {
            throw new InvalidRarityException();
        }

        Rarity newRarity = Rarity.builder().rarityID(UUID.randomUUID().toString().replace("-", "")).rarityType(request.getRarityType()).rarityLevel(request.getRarityLevel()).build();

        Rarity savedRarity = rarityRepository.save(newRarity);

        return CreateRarityResponseDTO.builder().rarityID(savedRarity.getRarityID()).build();
    }
}
