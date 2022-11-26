package com.kasintu.dtos.userdtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class UpdateUserRequestDTO {

    private String userID;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    private String password;
}
