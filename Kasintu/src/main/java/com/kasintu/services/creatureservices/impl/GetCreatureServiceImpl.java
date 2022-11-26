package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.services.creatureservices.GetCreatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCreatureServiceImpl implements GetCreatureService {

    private final CreatureRepository creatureRepository;

    @Override
    public Optional<CreatureDTO> getCreature(String creatureID)
    {
        return creatureRepository.findById(creatureID).map(CreatureDTOConverter::convertToDTO);
    }
}
