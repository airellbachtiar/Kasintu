package com.kasintu.dtos.playerdtos;

import com.kasintu.dtos.userdtos.UserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Generated
public class PlayerDTO extends UserDTO {
    private String playerID;
    private int coin;
}
