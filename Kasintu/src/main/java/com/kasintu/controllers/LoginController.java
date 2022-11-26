package com.kasintu.controllers;

import com.kasintu.dtos.logindtos.LoginRequestDTO;
import com.kasintu.dtos.logindtos.LoginResponseDTO;
import com.kasintu.services.loginservices.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = loginService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
