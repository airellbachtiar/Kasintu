package com.kasintu.dtos.userdtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class GetAllUsersResponseDTO {
    private List<UserDTO> users;
}
