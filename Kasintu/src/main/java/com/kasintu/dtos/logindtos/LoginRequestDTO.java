package com.kasintu.dtos.logindtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class LoginRequestDTO {
    
    private String username;
    private String password;
}
