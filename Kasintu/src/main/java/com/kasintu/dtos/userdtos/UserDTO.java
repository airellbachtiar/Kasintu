package com.kasintu.dtos.userdtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UserDTO {

    private String userID;
    private String username;
    private String email;

}
