package com.kasintu.services.adminservices;

import com.kasintu.dtos.admindtos.CreateAdminRequestDTO;
import com.kasintu.dtos.admindtos.CreateAdminResponseDTO;

public interface CreateAdminService {
    CreateAdminResponseDTO createAdmin(CreateAdminRequestDTO request);
}
