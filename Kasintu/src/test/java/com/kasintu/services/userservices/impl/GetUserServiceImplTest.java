package com.kasintu.services.userservices.impl;

import com.kasintu.dtos.userdtos.UserDTO;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetUserServiceImpl getUserService;

    @Test
    void getUser_ReturnUser()
    {
        User user = User.builder()
                .userID("1")
                .username("username")
                .password("password")
                .email("user@email.com")
                .startDate(LocalDate.now())
                .build();
        when(userRepositoryMock.findById("1")).thenReturn(Optional.of(user));

        Optional<UserDTO> userOptional = getUserService.getUser("1");
        UserDTO actualResult = userOptional.orElseThrow();

        UserDTO expectedResult = UserDTO.builder()
                .userID("1")
                .username("username")
                .email("user@email.com")
                .build();

        assertEquals(expectedResult, actualResult);
        verify(userRepositoryMock).findById("1");
    }
}