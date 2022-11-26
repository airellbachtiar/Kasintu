package com.kasintu.dtos.inventorydtos;

import com.kasintu.dtos.ownedcreatureddtos.OwnedCreatureDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class InventoryDTO {

    private String inventoryID;
    private String playerID;
    private List<OwnedCreatureDTO> ownedCreatures;

}
