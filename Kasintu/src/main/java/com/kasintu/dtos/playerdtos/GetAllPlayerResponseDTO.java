package com.kasintu.dtos.playerdtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllPlayerResponseDTO {
    List<PlayerDTO> players;
}
