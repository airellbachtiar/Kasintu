package com.kasintu.dtos.playerdtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreatePlayerRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
