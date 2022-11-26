package com.kasintu.controllers;

import com.kasintu.configs.security.isauthenticated.IsAuthenticated;
import com.kasintu.dtos.userdtos.UpdateUserRequestDTO;
import com.kasintu.services.userservices.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UpdateUserService updateUserService;

    @IsAuthenticated
    @PutMapping("{userID}")
    public ResponseEntity<Void> updateUser(@PathVariable("userID") String userID, @RequestBody @Valid UpdateUserRequestDTO request)
    {
        request.setUserID(userID);
        updateUserService.updateUser(request);
        return ResponseEntity.noContent().build();
    }
}
