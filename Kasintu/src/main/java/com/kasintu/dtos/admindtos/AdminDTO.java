package com.kasintu.dtos.admindtos;

import com.kasintu.dtos.userdtos.UserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class AdminDTO extends UserDTO {
    private String adminID;
}
