package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.GetAllUsersResponseDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.services.userservices.GetAllUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllUsersServiceImpl implements GetAllUsersService {

    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponseDTO getAllUsers() {
        return GetAllUsersResponseDTO.builder()
                .users(userRepository.findAll().stream().map(UserDTOConverter::convertToDTO).toList())
                .build();
    }
}
