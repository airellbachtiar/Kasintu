package com.kasintu.dtos.creaturedtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreateCreatureRequestDTO {
    private String name;
    private String rarityID;
    private String description;
}
