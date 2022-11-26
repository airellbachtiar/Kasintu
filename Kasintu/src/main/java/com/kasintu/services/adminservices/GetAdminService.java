package com.kasintu.services.adminservices;

import com.kasintu.dtos.admindtos.AdminDTO;

import java.util.Optional;

public interface GetAdminService {
    Optional<AdminDTO> getAdmin(String adminID);
}
