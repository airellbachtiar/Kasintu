package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.UpdateUserRequestDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.userservices.UpdateUserService;
import com.kasintu.services.userservices.exception.InvalidUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserServiceImpl implements UpdateUserService
{

    private final UserRepository userRepository;

    @Override
    public void updateUser(UpdateUserRequestDTO request)
    {
        Optional<User> user = userRepository.findById(request.getUserID());
        if(user.isEmpty())
        {
            throw new InvalidUserException();
        }
        if(!Objects.equals(user.get().getUsername(), request.getUsername()) && userRepository.existsByUsername(request.getUsername()))
        {
            throw new InvalidUserException("USER_EXIST");
        }
        User updateUser = user.get();

        updateUser.setEmail(request.getEmail());
        updateUser.setUsername(request.getUsername());

        if(StringUtils.hasText(request.getPassword()))
        {
            updateUser.setPassword(HashPassword.hashPassword(request.getPassword()));
        }
        userRepository.save(updateUser);
    }

}
