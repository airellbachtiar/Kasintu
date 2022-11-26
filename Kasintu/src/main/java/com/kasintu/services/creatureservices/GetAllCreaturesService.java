package com.kasintu.services.creatureservices;

import com.kasintu.dtos.creaturedtos.GetAllCreaturesRequestDTO;
import com.kasintu.dtos.creaturedtos.GetAllCreaturesResponseDTO;

public interface GetAllCreaturesService {
    GetAllCreaturesResponseDTO getAllCreatures(GetAllCreaturesRequestDTO request);
}
