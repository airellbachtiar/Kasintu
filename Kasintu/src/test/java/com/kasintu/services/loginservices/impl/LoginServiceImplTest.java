package com.kasintu.services.loginservices.impl;

import com.kasintu.dtos.logindtos.LoginRequestDTO;
import com.kasintu.dtos.logindtos.LoginResponseDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.repositories.PlayerRepository;
import com.kasintu.repositories.UserRepository;
import com.kasintu.repositories.entities.Admin;
import com.kasintu.repositories.entities.Player;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.loginservices.AccessTokenEncoder;
import com.kasintu.services.loginservices.exception.InvalidLoginException;
import com.kasintu.services.userservices.impl.HashPassword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {


    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @Test
    void login_Player_ReturnResponse() {
        User user = User.builder()
                .userID("1")
                .username("airell")
                .password(HashPassword.hashPassword("123"))
                .build();
        Player player = Player.builder()
                .playerID("2")
                .user(User.builder()
                        .userID("1")
                        .build())
                .build();
        when(userRepositoryMock.findByUsername("airell")).thenReturn(user);
        when(playerRepository.findByUser_UserID("1")).thenReturn(player);

        LoginRequestDTO request = LoginRequestDTO.builder()
                .username("airell")
                .password("123")
                .build();

        LoginResponseDTO actualResponse = loginService.login(request);
        assertNotNull(actualResponse);
        verify(userRepositoryMock).findByUsername("airell");
        verify(playerRepository).findByUser_UserID("1");
    }

    @Test
    void login_Admin_ReturnResponse() {
        User user = User.builder()
                .userID("1")
                .username("airell")
                .password(HashPassword.hashPassword("123"))
                .build();
        Admin admin = Admin.builder()
                .adminID("2")
                .user(User.builder()
                        .userID("1")
                        .build())
                .build();
        when(userRepositoryMock.findByUsername("airell")).thenReturn(user);
        when(adminRepository.findByUser_UserID("1")).thenReturn(admin);

        LoginRequestDTO request = LoginRequestDTO.builder()
                .username("airell")
                .password("123")
                .build();

        LoginResponseDTO actualResponse = loginService.login(request);
        assertNotNull(actualResponse);
        verify(userRepositoryMock).findByUsername("airell");
        verify(adminRepository).findByUser_UserID("1");
    }

    @Test
    void login_UserHasNoRole_ReturnException() {
        User user = User.builder()
                .userID("1")
                .username("airell")
                .password(HashPassword.hashPassword("123"))
                .build();
        when(userRepositoryMock.findByUsername("airell")).thenReturn(user);

        LoginRequestDTO request = LoginRequestDTO.builder()
                .username("airell")
                .password("123")
                .build();

        assertThrows(InvalidLoginException.class, ()-> loginService.login(request));
        verify(userRepositoryMock).findByUsername("airell");
    }

    @Test
    void login_UserIsNull_ReturnException() {
        when(userRepositoryMock.findByUsername("airell")).thenReturn(null);

        LoginRequestDTO request = LoginRequestDTO.builder()
                .username("airell")
                .password("123")
                .build();

        assertThrows(InvalidLoginException.class, ()-> loginService.login(request));
        verify(userRepositoryMock).findByUsername("airell");
    }

    @Test
    void login_PasswordIncorrect_ReturnException() {
        User user = User.builder()
                .userID("1")
                .username("airell")
                .password(HashPassword.hashPassword("123"))
                .build();
        when(userRepositoryMock.findByUsername("airell")).thenReturn(user);

        LoginRequestDTO request = LoginRequestDTO.builder()
                .username("airell")
                .password("1233")
                .build();

        assertThrows(InvalidLoginException.class, ()-> loginService.login(request));
        verify(userRepositoryMock).findByUsername("airell");
    }
}