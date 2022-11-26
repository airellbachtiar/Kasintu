package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.repositories.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
final class UserDTOConverter {

    public static UserDTO convertToDTO(User user)
    {
        return UserDTO.builder()
                .userID(user.getUserID())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
