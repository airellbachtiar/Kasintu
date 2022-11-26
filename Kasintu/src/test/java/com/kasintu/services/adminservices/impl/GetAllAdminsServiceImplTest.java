package com.kasintu.services.adminservices.impl;

import com.kasintu.dtos.admindtos.AdminDTO;
import com.kasintu.dtos.admindtos.GetAllAdminsResponseDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.repositories.entities.Admin;
import com.kasintu.repositories.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllAdminsServiceImplTest {

    @Mock
    private AdminRepository adminRepositoryMock;

    @InjectMocks
    private GetAllAdminsServiceImpl getAllAdminsService;

    @Test
    void getAllAdmin()
    {
        List<Admin> admins = List.of(
                Admin.builder()
                        .adminID("1")
                        .user(
                                User.builder()
                                        .userID("1")
                                        .build()
                        )
                        .build(),
                Admin.builder()
                        .adminID("2")
                        .user(
                                User.builder()
                                        .userID("2")
                                        .build()
                        )
                        .build()
        );
        when(adminRepositoryMock.findAll()).thenReturn(admins);

        GetAllAdminsResponseDTO actualResponse = getAllAdminsService.getAllAdmin();
        GetAllAdminsResponseDTO expectedResponse = GetAllAdminsResponseDTO.builder()
                .admins(
                        List.of(
                                AdminDTO.builder()
                                        .adminID("1")
                                        .userID("1")
                                        .build(),
                                AdminDTO.builder()
                                        .adminID("2")
                                        .userID("2")
                                        .build()
                        )
                )
                .build();

        assertEquals(expectedResponse, actualResponse);

        verify(adminRepositoryMock).findAll();
    }
}