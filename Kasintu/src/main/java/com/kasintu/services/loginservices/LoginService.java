package com.kasintu.services.loginservices;

import com.kasintu.dtos.logindtos.LoginRequestDTO;
import com.kasintu.dtos.logindtos.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO request);
}
