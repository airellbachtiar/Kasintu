package com.kasintu.dtos.ownedcreatureddtos;

import com.kasintu.dtos.creaturedtos.CreatureDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class OwnedCreatureDTO {
    private CreatureDTO creature;
    private String ownedCreatureID;
    private String inventoryID;
}
