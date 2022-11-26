package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.ownedcreatureddtos.OwnedCreatureDTO;
import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.services.ownedcreatureservices.GetOwnedCreatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetOwnedCreatureServiceImpl implements GetOwnedCreatureService {

    private final OwnedCreatureRepository ownedCreatureRepository;

    @Override
    public Optional<OwnedCreatureDTO> getOwnedCreature(String ownedCreatureID) {
        return ownedCreatureRepository.findById(ownedCreatureID).map(OwnedCreatureDTOConverter::convertToDTO);
    }
}
