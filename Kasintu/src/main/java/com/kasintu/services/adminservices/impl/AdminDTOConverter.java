package com.kasintu.services.adminservices.impl;

import com.kasintu.dtos.admindtos.AdminDTO;
import com.kasintu.repositories.entities.Admin;
import lombok.experimental.UtilityClass;

@UtilityClass
final class AdminDTOConverter {

    public static AdminDTO convertToDTO(Admin admin)
    {
        return AdminDTO.builder()
                .adminID(admin.getAdminID())
                .userID(admin.getUser().getUserID())
                .username(admin.getUser().getUsername())
                .email(admin.getUser().getEmail())
                .build();
    }
}
