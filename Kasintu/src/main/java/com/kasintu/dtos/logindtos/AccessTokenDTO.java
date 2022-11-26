package com.kasintu.dtos.logindtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class AccessTokenDTO
{
    private String subject;
    private List<String> roles;
    private String userID;

    @JsonIgnore
    public boolean hasRole(String roleName) {
        if (roles == null) {
            return false;
        }
        return roles.contains(roleName);
    }
}
