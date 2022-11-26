package com.kasintu.services.creatureservices.impl;

import com.kasintu.dtos.creaturedtos.GetAllCreaturesRequestDTO;
import com.kasintu.dtos.creaturedtos.GetAllCreaturesResponseDTO;
import com.kasintu.repositories.CreatureRepository;
import com.kasintu.repositories.entities.Creature;
import com.kasintu.services.creatureservices.GetAllCreaturesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCreaturesServiceImpl implements GetAllCreaturesService {

    private final CreatureRepository creatureRepository;

    @Override
    public GetAllCreaturesResponseDTO getAllCreatures(GetAllCreaturesRequestDTO request) {
        List<Creature> result;
        if(StringUtils.hasText(request.getRarityID()))
        {
            result = creatureRepository.findAllByRarity_RarityIDOrderByName(request.getRarityID());
        }
        else
        {
            result = creatureRepository.findAll();
        }

        final GetAllCreaturesResponseDTO response= new GetAllCreaturesResponseDTO();
        response.setCreatures(result.stream().map(CreatureDTOConverter::convertToDTO).toList());

        return response;
    }
}
