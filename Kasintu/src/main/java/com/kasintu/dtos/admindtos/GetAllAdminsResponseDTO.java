package com.kasintu.dtos.admindtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllAdminsResponseDTO {
    private List<AdminDTO> admins;
}
