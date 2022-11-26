package com.kasintu.dtos.inventorydtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllInventoriesResponseDTO {
    private List<InventoryDTO> inventories;
}
