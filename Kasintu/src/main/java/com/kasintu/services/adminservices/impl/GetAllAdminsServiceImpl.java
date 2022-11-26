package com.kasintu.services.adminservices.impl;

import com.kasintu.dtos.admindtos.GetAllAdminsResponseDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.services.adminservices.GetAllAdminsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllAdminsServiceImpl implements GetAllAdminsService
{

    private final AdminRepository adminRepository;

    @Override
    public GetAllAdminsResponseDTO getAllAdmin() {
        return GetAllAdminsResponseDTO.builder()
                .admins(adminRepository.findAll().stream().map(AdminDTOConverter::convertToDTO).toList())
                .build();
    }
}
