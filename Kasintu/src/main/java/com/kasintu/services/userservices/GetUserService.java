package com.kasintu.services.userservices;

import com.kasintu.dtos.userdtos.UserDTO;

import java.util.Optional;

public interface GetUserService {
    Optional<UserDTO> getUser(String userID);
}
