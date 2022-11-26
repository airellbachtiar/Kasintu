package com.kasintu.dtos.inventorydtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetInventoryRequestDTO {
    private String inventoryID;
    private String playerID;
}
