package com.kasintu.dtos.ownedcreatureddtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllOwnedCreaturesResponseDTO {
    private List<OwnedCreatureDTO> ownedCreatures;
}
