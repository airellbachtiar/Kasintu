package com.kasintu.dtos.admindtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class CreateAdminRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
