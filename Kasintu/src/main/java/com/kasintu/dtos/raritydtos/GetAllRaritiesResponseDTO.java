package com.kasintu.dtos.raritydtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllRaritiesResponseDTO {
    private List<RarityDTO> rarities;
}
