package com.kasintu.dtos.userdtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreateUserRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
