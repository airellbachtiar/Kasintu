package com.kasintu.dtos.creaturedtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllCreaturesResponseDTO {
    List<CreatureDTO> creatures;
}
