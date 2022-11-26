package com.kasintu.dtos.raritydtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class RarityDTO {
    private String rarityID;
    private String rarityType;
    private Integer rarityLevel;
}
