package com.kasintu.services.ownedcreatureservices.impl;

import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesResponseDTO;
import com.kasintu.repositories.OwnedCreatureRepository;
import com.kasintu.repositories.entities.OwnedCreature;
import com.kasintu.services.ownedcreatureservices.GetAllOwnedCreaturesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllOwnedCreaturesServiceImpl implements GetAllOwnedCreaturesService {

    private final OwnedCreatureRepository ownedCreatureRepository;

    @Override
    public GetAllOwnedCreaturesResponseDTO getAllOwnedCreatures(GetAllOwnedCreaturesRequestDTO request)
    {
        List<OwnedCreature> result;
        if(StringUtils.hasText(request.getInventoryID()))
        {
            result = ownedCreatureRepository.findAllByInventory_InventoryID(request.getInventoryID());
        }
        else
        {
            result = ownedCreatureRepository.findAll();
        }

        return GetAllOwnedCreaturesResponseDTO.builder().ownedCreatures(result.stream().map(OwnedCreatureDTOConverter::convertToDTO).toList()).build();
    }
}
