package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.GetAllUsersResponseDTO;
import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllUsersServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetAllUsersServiceImpl getAllUsersService;

    @Test
    void getAllUsers_ReturnUsers()
    {
        List<User> users = List.of(
                User.builder()
                        .userID("1")
                        .email("user1@email.com")
                        .username("username1")
                        .password("password")
                        .startDate(LocalDate.now())
                        .build(),
                User.builder()
                        .userID("2")
                        .email("user2@email.com")
                        .username("username2")
                        .password("password")
                        .startDate(LocalDate.now())
                        .build()
        );
        when(userRepositoryMock.findAll()).thenReturn(users);

        GetAllUsersResponseDTO actualResponse = getAllUsersService.getAllUsers();
        GetAllUsersResponseDTO expectedResponse = GetAllUsersResponseDTO.builder()
                .users(
                        List.of(
                                UserDTO.builder()
                                        .userID("1")
                                        .email("user1@email.com")
                                        .username("username1")
                                        .build(),
                                UserDTO.builder()
                                        .userID("2")
                                        .email("user2@email.com")
                                        .username("username2")
                                        .build()
                        )
                )
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(userRepositoryMock).findAll();
    }
}