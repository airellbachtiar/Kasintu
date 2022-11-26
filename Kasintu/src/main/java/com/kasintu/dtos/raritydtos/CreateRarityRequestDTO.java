package com.kasintu.dtos.raritydtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreateRarityRequestDTO {
    private String rarityType;
    private Integer rarityLevel;
}
