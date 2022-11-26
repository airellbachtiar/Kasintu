package com.kasintu.services.rarityservices.impl;

import com.kasintu.repositories.RarityRepository;
import com.kasintu.services.rarityservices.DeleteRarityService;
import com.kasintu.services.rarityservices.exception.InvalidRarityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteRarityServiceImpl implements DeleteRarityService {

    private final RarityRepository rarityRepository;

    @Transactional
    @Override
    public void deleteRarity(String rarityID)
    {
        if(!rarityRepository.existsById(rarityID))
        {
            throw new InvalidRarityException("RARITY_ID_INVALID");
        }
        rarityRepository.deleteById(rarityID);
    }
}
