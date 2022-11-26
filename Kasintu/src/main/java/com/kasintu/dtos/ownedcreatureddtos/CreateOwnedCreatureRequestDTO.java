package com.kasintu.dtos.ownedcreatureddtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreateOwnedCreatureRequestDTO {
    private String creatureID;
    private String inventoryID;
}
