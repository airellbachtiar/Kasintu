package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.services.userservices.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService
{

    private final UserRepository userRepository;

    @Override
    public Optional<UserDTO> getUser(String userID) {
        return userRepository.findById(userID).map(UserDTOConverter::convertToDTO);
    }
}
