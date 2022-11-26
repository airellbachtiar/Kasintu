package com.kasintu.services.ownedcreatureservices;

import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesRequestDTO;
import com.kasintu.dtos.ownedcreatureddtos.GetAllOwnedCreaturesResponseDTO;

public interface GetAllOwnedCreaturesService {
    GetAllOwnedCreaturesResponseDTO getAllOwnedCreatures(GetAllOwnedCreaturesRequestDTO request);
}
