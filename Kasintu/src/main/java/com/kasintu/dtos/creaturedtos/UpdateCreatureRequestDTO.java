package com.kasintu.dtos.creaturedtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UpdateCreatureRequestDTO {
    private String creatureID;
    private String name;
    private String rarityID;
    private String description;
}
