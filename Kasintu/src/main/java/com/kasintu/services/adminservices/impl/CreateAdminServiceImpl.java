package com.kasintu.services.adminservices.impl;

import com.kasintu.dtos.admindtos.CreateAdminRequestDTO;
import com.kasintu.dtos.admindtos.CreateAdminResponseDTO;
import com.kasintu.dtos.userdtos.CreateUserRequestDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.repositories.entities.Admin;
import com.kasintu.repositories.entities.User;
import com.kasintu.services.adminservices.CreateAdminService;
import com.kasintu.services.userservices.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateAdminServiceImpl implements CreateAdminService
{

    private final AdminRepository adminRepository;
    private final CreateUserService createUserService;


    @Override
    public CreateAdminResponseDTO createAdmin(CreateAdminRequestDTO request) {

        User savedUser = createUserService.createUser(
                CreateUserRequestDTO.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .username(request.getUsername())
                        .build());

        String adminID = UUID.randomUUID().toString().replace("-", "");

        Admin savedAdmin = adminRepository.save(Admin.builder()
                        .adminID(adminID)
                        .user(savedUser)
                .build());

        return CreateAdminResponseDTO.builder().adminID(savedAdmin.getAdminID()).build();
    }
}
