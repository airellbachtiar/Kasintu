package com.kasintu.services.userservices;

import com.kasintu.dtos.userdtos.CreateUserRequestDTO;
import com.kasintu.repositories.entities.User;

public interface CreateUserService {
    User createUser(CreateUserRequestDTO request);
}
