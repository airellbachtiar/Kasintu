package com.kasintu.dtos.ownedcreatureddtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UpdateOwnedCreatureRequestDTO {
    private String ownedCreatureID;
    private String inventoryID;
}
