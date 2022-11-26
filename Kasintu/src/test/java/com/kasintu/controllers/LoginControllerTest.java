package com.kasintu.controllers;

import com.kasintu.dtos.logindtos.LoginRequestDTO;
import com.kasintu.dtos.logindtos.LoginResponseDTO;
import com.kasintu.services.loginservices.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    void login() throws Exception {
        LoginRequestDTO expectedRequest = LoginRequestDTO.builder()
                .username("username")
                .password("password")
                .build();
        when(loginService.login(expectedRequest)).thenReturn(
                LoginResponseDTO.builder()
                        .accessToken("123")
                        .build()
        );

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                            {
                                "username": "username",
                                "password": "password"
                            }
                        """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "accessToken": "123"
                        }
                    """));
        verify(loginService).login(expectedRequest);
    }
}