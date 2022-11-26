package com.kasintu.services.adminservices.impl;

import com.kasintu.dtos.admindtos.AdminDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.repositories.entities.Admin;
import com.kasintu.repositories.entities.User;
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
class GetAdminServiceImplTest {

    @Mock
    private AdminRepository adminRepositoryMock;

    @InjectMocks
    private GetAdminServiceImpl getAdminService;

    @Test
    void getAdmin()
    {
        Admin admin = Admin.builder()
                .adminID("1")
                .user(
                        User.builder()
                                .userID("1")
                                .build()
                )
                .build();
        when(adminRepositoryMock.findById("1")).thenReturn(Optional.of(admin));

        Optional<AdminDTO> adminOptional = getAdminService.getAdmin("1");
        AdminDTO actualResult = adminOptional.orElseThrow();

        AdminDTO expectedResult = AdminDTO.builder()
                .adminID("1")
                .userID("1")
                .build();

        assertEquals(expectedResult, actualResult);
        verify(adminRepositoryMock).findById("1");
    }
}