package com.kasintu.dtos.playerdtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UpdatePlayerRequestDTO {
    private String playerID;
    private int coin;
}
