package com.kasintu.dtos.creaturedtos;

import com.kasintu.dtos.raritydtos.RarityDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreatureDTO {
    private String creatureID;
    private String name;
    private RarityDTO rarity;
    private String description;
}
