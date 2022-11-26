package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.UpdateUserRequestDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.userservices.exception.InvalidUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UpdateUserServiceImpl updateUserService;

    @Test
    void updateUser_Successful()
    {
        User userBeforeUpdate = User.builder()
                .userID("1")
                .username("username")
                .password("password")
                .email("user@email.com")
                .build();
        when(userRepositoryMock.findById("1")).thenReturn(Optional.of(userBeforeUpdate));

        UpdateUserRequestDTO request =UpdateUserRequestDTO.builder()
                .userID("1")
                .username("NewUsername")
                .password("NewPassword")
                .email("new@email.com")
                .build();
        updateUserService.updateUser(request);

        User expectedUserSaved = User.builder()
                .userID("1")
                .username("NewUsername")
                .password("c2a3134ea69eeec1689bf809385f9de47dcafc8484896df10cbaefeb61eeb960")
                .email("new@email.com")
                .build();

        verify(userRepositoryMock).findById("1");
        verify(userRepositoryMock).save(expectedUserSaved);
    }

    @Test
    void updateUser_UsernameAlreadyExist_ThrowException()
    {
        User userBeforeUpdate = User.builder()
                .userID("1")
                .username("username")
                .password("password")
                .email("user@email.com")
                .build();
        when(userRepositoryMock.findById("1")).thenReturn(Optional.of(userBeforeUpdate));
        when(userRepositoryMock.existsByUsername("NewUsername")).thenReturn(true);

        UpdateUserRequestDTO request =UpdateUserRequestDTO.builder()
                .userID("1")
                .username("NewUsername")
                .password("NewPassword")
                .email("new@email.com")
                .build();
        assertThrows(InvalidUserException.class, ()-> updateUserService.updateUser(request));

        verify(userRepositoryMock).findById("1");
    }

    @Test
    void updateUser_UserNotFound_ThrowException()
    {
        when(userRepositoryMock.findById("1")).thenReturn(Optional.empty());

        UpdateUserRequestDTO request =UpdateUserRequestDTO.builder()
                .userID("1")
                .username("NewUsername")
                .password("NewPassword")
                .email("new@email.com")
                .build();
        assertThrows(InvalidUserException.class, ()-> updateUserService.updateUser(request));

        verify(userRepositoryMock).findById("1");
    }
}