package com.kasintu.controllers;

import com.kasintu.dtos.userdtos.UpdateUserRequestDTO;
import com.kasintu.services.userservices.UpdateUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UpdateUserService updateUserService;

    @Test
    void updateUser_Return204() throws Exception {
        mockMvc.perform(put("/users/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "userID": "1",
                            "username": "username",
                            "email": "email",
                            "password": "password"
                        }
                        """))
                .andDo(print())
                .andExpect(status().isNoContent());

        UpdateUserRequestDTO expectedRequest = UpdateUserRequestDTO.builder()
                .userID("1")
                .username("username")
                .email("email")
                .password("password")
                .build();
        verify(updateUserService).updateUser(expectedRequest);
    }
}