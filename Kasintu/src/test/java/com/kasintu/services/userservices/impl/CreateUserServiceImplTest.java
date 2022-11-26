package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.CreateUserRequestDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.userservices.exception.InvalidUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private CreateUserServiceImpl createUserService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void createUser_ReturnNewUser()
    {
        User savedUser = User.builder()
                .userID("1")
                .username("username")
                .password("password")
                .email("user@email.com")
                .startDate(LocalDate.now())
                .build();
        when(userRepositoryMock.save(any())).thenReturn(savedUser);

        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .username("username")
                .password("password")
                .email("user@email.com")
                .build();
        User actualResponse = createUserService.createUser(request);

        assertThat(actualResponse).usingRecursiveComparison().ignoringFields("userID").isEqualTo(savedUser);
        assertEquals(savedUser, actualResponse);
        verify(userRepositoryMock).save(userCaptor.capture());
    }

    @Test
    void createUser_UsernameExist_ThrowException()
    {
        CreateUserRequestDTO request = CreateUserRequestDTO.builder()
                .username("username")
                .password("password")
                .email("user@email.com")
                .build();
        when(userRepositoryMock.existsByUsername("username")).thenReturn(true);
        assertThrows(InvalidUserException.class, ()->createUserService.createUser(request));

        verify(userRepositoryMock).existsByUsername("username");
    }
}