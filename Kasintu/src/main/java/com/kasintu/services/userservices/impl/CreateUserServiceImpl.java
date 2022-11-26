package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.CreateUserRequestDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.userservices.CreateUserService;
import com.kasintu.services.userservices.exception.InvalidUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User createUser(CreateUserRequestDTO request) {
        if(userRepository.existsByUsername(request.getUsername()))
        {
            throw new InvalidUserException("USERNAME_EXIST");
        }

        String userID = UUID.randomUUID().toString().replace("-", "");
        String hashedPassword = HashPassword.hashPassword(request.getPassword());

        User newUser = User.builder()
                .userID(userID)
                .email(request.getEmail())
                .password(hashedPassword)
                .username(request.getUsername())
                .startDate(LocalDate.now())
                .build();
        return userRepository.save(newUser);
    }
}
