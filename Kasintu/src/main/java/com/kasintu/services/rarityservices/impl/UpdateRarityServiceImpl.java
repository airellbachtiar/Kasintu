package com.kasintu.services.rarityservices.impl;

import com.kasintu.dtos.raritydtos.UpdateRarityRequestDTO;
import com.kasintu.repositories.RarityRepository;
import com.kasintu.repositories.entities.Rarity;
import com.kasintu.services.rarityservices.UpdateRarityService;
import com.kasintu.services.rarityservices.exception.InvalidRarityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateRarityServiceImpl implements UpdateRarityService {

    private final RarityRepository rarityRepository;

    @Transactional
    @Override
    public void updateRarity(UpdateRarityRequestDTO request) {
        Optional<Rarity> rarity = rarityRepository.findById(request.getRarityID());
        if(rarity.isEmpty())
        {
            throw new InvalidRarityException("RARITY_ID_INVALID");
        }
        Rarity updateRarity = rarity.get();

        if(!Objects.equals(updateRarity.getRarityType(), request.getRarityType()) && rarityRepository.existsByRarityType(request.getRarityType()))
        {
            throw new InvalidRarityException("RARITY_TYPE_ALREADY_EXIST");
        }

        updateRarity.setRarityType(request.getRarityType());
        updateRarity.setRarityLevel(request.getRarityLevel());

        rarityRepository.save(updateRarity);
    }
}
