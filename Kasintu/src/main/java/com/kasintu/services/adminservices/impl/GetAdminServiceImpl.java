package com.kasintu.services.adminservices.impl;

import com.kasintu.dtos.admindtos.AdminDTO;
import com.kasintu.repositories.AdminRepository;
import com.kasintu.services.adminservices.GetAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetAdminServiceImpl implements GetAdminService {

    private final AdminRepository adminRepository;

    @Override
    public Optional<AdminDTO> getAdmin(String adminID) {
        return adminRepository.findById(adminID).map(AdminDTOConverter::convertToDTO);
    }
}
