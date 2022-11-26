package com.kasintu.services.gameservices;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import com.kasintu.dtos.userdtos.UserDTO;

import java.util.List;

public interface SaveCreatureToInventoryService {
    boolean saveCreatureToInventory(List<CreatureDTO> creatures, UserDTO userDTO);
}
