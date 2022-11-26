package com.kasintu.services.adminservices.impl;

import com.kasintu.dtos.admindtos.CreateAdminRequestDTO;
import com.kasintu.dtos.admindtos.CreateAdminResponseDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.repositories.entities.Admin;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.userservices.CreateUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAdminServiceImplTest {

    @Mock
    private AdminRepository adminRepositoryMock;

    @Mock
    private CreateUserService createUserService;

    @InjectMocks
    private CreateAdminServiceImpl createAdminService;

    @Captor
    private ArgumentCaptor<Admin> adminCaptor;

    @Test
    void createAdmin()
    {
        Admin savedAdmin = Admin.builder()
                .adminID("1")
                .user(
                        User.builder()
                                .userID("1")
                                .build()
                )
                .build();
        when(adminRepositoryMock.save(any())).thenReturn(savedAdmin);

        CreateAdminRequestDTO request = CreateAdminRequestDTO.builder()
                .email("admin@email.com")
                .password("password")
                .username("admin")
                .build();
        CreateAdminResponseDTO actualResponse = createAdminService.createAdmin(request);
        CreateAdminResponseDTO expectedResponse = CreateAdminResponseDTO.builder()
                .adminID("1")
                .build();

        assertEquals(expectedResponse, actualResponse);
        verify(adminRepositoryMock).save(adminCaptor.capture());
    }
}